package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.CartItem;
import ict.finki.store26springapi.model.ShoppingCart;
import ict.finki.store26springapi.model.dto.CartItemDto;
import ict.finki.store26springapi.model.dto.ProductDto;
import ict.finki.store26springapi.model.exceptions.ShoppingCartNotFoundException;
import ict.finki.store26springapi.service.CartItemService;
import ict.finki.store26springapi.service.ShoppingCartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, CartItemService cartItemService) {
        this.shoppingCartService = shoppingCartService;
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{userId}/shoppingCart")
    public ResponseEntity<ShoppingCart> findByUserId(@PathVariable Long userId) {
        return this.shoppingCartService.findByUserId(userId)
                .map(shoppingCart -> ResponseEntity.ok(shoppingCart))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/shoppingCart/items")
    public ResponseEntity<List<CartItemDto>> listCartItems(@PathVariable Long userId) {
        List<CartItemDto> cartItemDtos = cartItemService.getAllCartItemsByUserId(userId);
        return ResponseEntity.ok(cartItemDtos);
    }

    @PostMapping("/{userId}/shoppingCart/cartItem")
    public ResponseEntity<CartItemDto> addCartItemToShoppingCart(@PathVariable Long userId, @RequestBody CartItemDto cartItemDto) {
        return this.cartItemService.addCartItem(cartItemDto)
                .map(cartItemDto1 -> ResponseEntity.ok(cartItemDto1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{userId}/shoppingCart/cartItem/{cartItemId}")
    public ResponseEntity<String> deleteCartItemFromShoppingCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        try {
            cartItemService.deleteCartItemById(cartItemId);
            return ResponseEntity.ok("CartItem deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete CartItem: " + e.getMessage());
        }
    }

}
