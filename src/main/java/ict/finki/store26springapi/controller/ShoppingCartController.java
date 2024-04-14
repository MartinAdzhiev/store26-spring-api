package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.ShoppingCart;
import ict.finki.store26springapi.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{userId}/shoppingCart")
    public ResponseEntity<ShoppingCart> findByUserId(@PathVariable Long userId) {
        return this.shoppingCartService.findByUserId(userId)
                .map(shoppingCart -> ResponseEntity.ok(shoppingCart))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
