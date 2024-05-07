package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.*;
import ict.finki.store26springapi.model.dto.CartItemDto;
import ict.finki.store26springapi.model.exceptions.ProductNotFoundException;
import ict.finki.store26springapi.model.exceptions.ShoppingCartNotFoundException;
import ict.finki.store26springapi.model.exceptions.SizeNotFoundException;
import ict.finki.store26springapi.repository.*;
import ict.finki.store26springapi.service.CartItemService;
import ict.finki.store26springapi.service.ProductService;
import jakarta.transaction.Transactional;
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

    private final ProductService productService;

    public CartItemImpl(CartItemRepository cartItemRepository, ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, SizeRepository sizeRepository, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
        this.productService = productService;
    }

    @Override
    public Optional<CartItemDto> addCartItem(Long cartId, Long productId, Long sizeId,CartItemDto cartItemDto) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException(cartId));

        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Size size = this.sizeRepository.findById(sizeId)
                .orElseThrow(() -> new SizeNotFoundException(sizeId));

        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setProduct(product);
        cartItem.setSize(size);
        cartItem.setQuantity(cartItemDto.getQuantity());

        CartItem saved = this.cartItemRepository.save(cartItem);

        return Optional.of(this.getDto(saved));
    }

    @Override
    @Transactional
    public List<CartItemDto> getAllCartItemsByShoppingCartId(Long cartId) {
        List<CartItem> cartItems = this.cartItemRepository.findAllByShoppingCart_Id(cartId);
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
        cartItemDto.setProductCartItemResponse(productService.getProductInCartItem(cartItem.getProduct()));
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setSizeId(cartItem.getSize().getId());
        cartItemDto.setSizeName(cartItem.getSize().getName());

        return cartItemDto;
    }
}

