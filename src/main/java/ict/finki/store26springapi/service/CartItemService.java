package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.CartItem;
import ict.finki.store26springapi.model.dto.CartItemDto;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    public Optional<CartItemDto> addCartItem(CartItemDto cartItemDto);

    List<CartItemDto> getAllCartItemsByUserId(Long id);

    Optional<CartItem> getCartItemById(Long id);

    void deleteCartItemById(Long id);

    CartItemDto getDto(CartItem cartItem);
}
