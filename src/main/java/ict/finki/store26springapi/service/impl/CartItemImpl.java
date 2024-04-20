package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.*;
import ict.finki.store26springapi.model.dto.CartItemDto;
import ict.finki.store26springapi.model.exceptions.ProductNotFoundException;
import ict.finki.store26springapi.model.exceptions.ShoppingCartNotFoundException;
import ict.finki.store26springapi.model.exceptions.SizeNotFoundException;
import ict.finki.store26springapi.repository.*;
import ict.finki.store26springapi.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;

    public CartItemImpl(CartItemRepository cartItemRepository, ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, SizeRepository sizeRepository) {
        this.cartItemRepository = cartItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Optional<CartItemDto> addCartItem(CartItemDto cartItemDto) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartItemDto.getShoppingCartId())
                .orElseThrow(() -> new ShoppingCartNotFoundException(cartItemDto.getShoppingCartId()));

        Product product = this.productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(cartItemDto.getProductId()));

        Size size = this.sizeRepository.findById(cartItemDto.getSizeId())
                .orElseThrow(() -> new SizeNotFoundException(cartItemDto.getSizeId()));

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setProduct(product);
        cartItem.setSize(size);
        cartItem.setQuantity(cartItemDto.getQuantity());

        CartItem saved = this.cartItemRepository.save(cartItem);

        return Optional.of(this.getDto(saved));
    }

    @Override
    public List<CartItemDto> getAllCartItemsByUserId(Long id) {
        List<CartItem> cartItems = this.cartItemRepository.findAll();
        return cartItems.stream().map(cartItem -> this.getDto(cartItem))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return this.cartItemRepository.findById(id);
    }

    @Override
    public void deleteCartItemById(Long id) {
        this.cartItemRepository.deleteById(id);
    }

    @Override
    public CartItemDto getDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setId(cartItem.getId());
        cartItemDto.setShoppingCartId(cartItem.getShoppingCart().getId());
        cartItemDto.setProductId(cartItem.getProduct().getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setSizeId(cartItem.getSize().getId());

        return cartItemDto;
    }
}

