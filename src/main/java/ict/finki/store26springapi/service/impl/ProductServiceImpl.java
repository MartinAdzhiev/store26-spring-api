package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.Category;
import ict.finki.store26springapi.model.Product;
import ict.finki.store26springapi.model.Size;
import ict.finki.store26springapi.model.dto.ProductCartItemResponse;
import ict.finki.store26springapi.model.dto.ProductDto;
import ict.finki.store26springapi.model.dto.ProductOrderItemResponse;
import ict.finki.store26springapi.model.dto.ReviewDto;
import ict.finki.store26springapi.model.exceptions.CategoryNotFoundException;
import ict.finki.store26springapi.model.exceptions.ProductNotFoundException;
import ict.finki.store26springapi.repository.CategoryRepository;
import ict.finki.store26springapi.repository.ProductRepository;
import ict.finki.store26springapi.repository.SizeRepository;
import ict.finki.store26springapi.service.ProductService;
import ict.finki.store26springapi.service.ReviewService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final SizeRepository sizeRepository;

    private final ReviewService reviewService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, SizeRepository sizeRepository, ReviewService reviewService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.sizeRepository = sizeRepository;
        this.reviewService = reviewService;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> this.getDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> save(ProductDto productDto) throws IOException {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategoryId()));

        Product product = new Product();

        product.setName(productDto.getName());
        product.setGender(productDto.getGender());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setColor(productDto.getColor());
        product.setImage(productDto.getImage().getBytes());
        product.setCategory(category);

        Product saved = productRepository.save(product);

        return Optional.of(this.getDto(saved));
    }

    @Override
    public Optional<ProductDto> edit(Long id, ProductDto productDto) throws IOException {
        Product product = this.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategoryId()));

        product.setName(productDto.getName());
        product.setGender(productDto.getGender());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setColor(productDto.getColor());
        product.setImage(productDto.getImage().getBytes());
        product.setCategory(category);


        return Optional.of(this.getDto(productRepository.save(product)));


    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public ProductDto getDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setGender(product.getGender());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setColor(product.getColor());
        productDto.setByteImage(product.getImage());
        productDto.setCategoryId(product.getCategory().getId());

        List<Size> sizes = sizeRepository.findAllByProductId(product.getId());
        productDto.setSizes(sizes);

        List<ReviewDto> reviews = reviewService.findAllByProductId(product.getId());
        productDto.setReviews(reviews);

        return productDto;
    }

    @Override
    public ProductCartItemResponse getProductInCartItem(Product product) {
        ProductCartItemResponse productCartItemResponse = new ProductCartItemResponse();

        productCartItemResponse.setId(product.getId());
        productCartItemResponse.setName(product.getName());
        productCartItemResponse.setGender(product.getGender());
        productCartItemResponse.setColor(product.getColor());
        productCartItemResponse.setPrice(product.getPrice());
        productCartItemResponse.setByteImage(product.getImage());

        return productCartItemResponse;
    }

    @Override
    public ProductOrderItemResponse getProductInOrderItem(Product product) {
        ProductOrderItemResponse productOrderItemResponse = new ProductOrderItemResponse();

        productOrderItemResponse.setId(product.getId());
        productOrderItemResponse.setName(product.getName());
        productOrderItemResponse.setGender(product.getGender());
        productOrderItemResponse.setColor(product.getColor());
        productOrderItemResponse.setPrice(product.getPrice());
        productOrderItemResponse.setByteImage(product.getImage());

        return productOrderItemResponse;
    }
}
