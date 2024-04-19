package ict.finki.store26springapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private LocalDate createdOn;

    private String description;

    private double rating;

    private Long userId;

    private Long productId;
}
