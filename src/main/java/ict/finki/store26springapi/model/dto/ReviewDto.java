package ict.finki.store26springapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    Long id;

    private LocalDate createdOn;

    private String description;

    private double rating;

    private String user;

    private Long productId;
}
