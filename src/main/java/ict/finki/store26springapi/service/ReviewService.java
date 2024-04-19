package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.Review;
import ict.finki.store26springapi.model.dto.ReviewDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDto> findAll();

    Optional<Review> findById(Long id);

    List<ReviewDto> findAllByUserId(Long userId);

    List<ReviewDto> findAllByProductId(Long productId);

    Optional<ReviewDto> save(ReviewDto reviewDto) throws IOException;

    ReviewDto getDto(Review review);

    void deleteById(Long id);
}
