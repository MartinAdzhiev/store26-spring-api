package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.Product;
import ict.finki.store26springapi.model.Review;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.ReviewDto;
import ict.finki.store26springapi.model.exceptions.ProductNotFoundException;
import ict.finki.store26springapi.model.exceptions.UserNotFoundException;
import ict.finki.store26springapi.repository.ProductRepository;
import ict.finki.store26springapi.repository.ReviewRepository;
import ict.finki.store26springapi.repository.UserRepository;
import ict.finki.store26springapi.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ReviewDto> findAll() {
        List<Review> reviews = reviewRepository.findAll();

        return reviews.stream().map(review -> this.getDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    @Transactional
    public List<ReviewDto> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<Review> reviews = reviewRepository.findAllByUserId(userId);

        return reviews.stream().map(review -> this.getDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> findAllByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        List<Review> reviews = reviewRepository.findAllByProduct(product);

        return reviews.stream().map(review -> this.getDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDto> save(Long userId, Long productId, ReviewDto reviewDto) throws IOException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Review review = new Review();
        review.setCreatedOn(LocalDate.now());
        review.setDescription(reviewDto.getDescription());
        review.setRating(reviewDto.getRating());
        review.setUser(user);
        review.setProduct(product);

        Review saved = reviewRepository.save(review);

        return Optional.of(this.getDto(saved));
    }

    @Override
    public ReviewDto getDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setCreatedOn(review.getCreatedOn());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setRating(review.getRating());
        reviewDto.setUser(review.getUser().getFirstName() + " " + review.getUser().getLastName());
        reviewDto.setProductId(review.getProduct().getId());

        return reviewDto;
    }

    @Override
    public void deleteById(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
