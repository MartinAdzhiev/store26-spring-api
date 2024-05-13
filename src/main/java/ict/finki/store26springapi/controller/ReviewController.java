package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.dto.ReviewDto;
import ict.finki.store26springapi.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/review/all")
    public ResponseEntity<List<ReviewDto>> findAll() {
        List<ReviewDto> reviewDtos = reviewService.findAll();
        return ResponseEntity.ok(reviewDtos);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<ReviewDto> findById(@PathVariable Long id) {
        return this.reviewService.findById(id)
                .map(review -> ResponseEntity.ok(reviewService.getDto(review)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/review")
    public ResponseEntity<List<ReviewDto>> findByUserId(@PathVariable Long id) {
        List<ReviewDto> reviewDtos = reviewService.findAllByUserId(id);
        return ResponseEntity.ok(reviewDtos);
    }

    @GetMapping("/product/{id}/review")
    public ResponseEntity<List<ReviewDto>> findByProductId(@PathVariable Long id) {
        List<ReviewDto> reviewDtos = reviewService.findAllByProductId(id);
        return ResponseEntity.ok(reviewDtos);
    }

    @PostMapping("/{userId}/product/{productId}/review/add")
    public ResponseEntity<ReviewDto> addReview(@PathVariable Long userId, @PathVariable Long productId, @RequestBody ReviewDto reviewDto) throws IOException {
        System.out.println(reviewDto);
        return this.reviewService.save(userId, productId, reviewDto)
                .map(reviewDto1 -> ResponseEntity.ok(reviewDto1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/review/{id}/delete")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.reviewService.deleteById(id);

        if (this.reviewService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
