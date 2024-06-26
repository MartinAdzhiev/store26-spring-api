package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.Product;
import ict.finki.store26springapi.model.dto.CreditCardDto;
import ict.finki.store26springapi.model.dto.ProductCartItemResponse;
import ict.finki.store26springapi.model.dto.ProductDto;
import ict.finki.store26springapi.model.dto.ProductOrderItemResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    List<ProductDto> findAll();

    Optional<ProductDto> save(ProductDto productDto) throws IOException;

    Optional<ProductDto> edit(Long id, ProductDto productDto) throws IOException;

    void deleteById(Long id);

    ProductDto getDto(Product product);

    ProductCartItemResponse getProductInCartItem(Product product);

    ProductOrderItemResponse getProductInOrderItem(Product product);
}
