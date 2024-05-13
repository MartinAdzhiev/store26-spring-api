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

    @GetMapping("/shoppingCart/{cartId}/items")
    public ResponseEntity<List<CartItemDto>> listCartItems(@PathVariable Long cartId) {
        List<CartItemDto> cartItemDtos = cartItemService.getAllCartItemsByShoppingCartId(cartId);
        return ResponseEntity.ok(cartItemDtos);
    }

    @PostMapping("/shoppingCart/{cartId}/product/{productId}/size/{sizeId}/addCartItem")
    public ResponseEntity<CartItemDto> addCartItemToShoppingCart(@PathVariable Long cartId,
                                                                 @PathVariable Long productId,
                                                                 @PathVariable Long sizeId,
                                                                 @RequestBody CartItemDto cartItemDto) {
        return this.cartItemService.addCartItem(cartId, productId, sizeId, cartItemDto)
                .map(cartItemDto1 -> ResponseEntity.ok(cartItemDto1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/cartItem/{cartItemId}/delete")
    public ResponseEntity<String> deleteCartItemFromShoppingCart(@PathVariable Long cartItemId) {
        this.cartItemService.deleteCartItemById(cartItemId);

        if (this.cartItemService.getCartItemById(cartItemId).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
