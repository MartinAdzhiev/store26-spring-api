package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductDto {

    private String name;

    private Gender gender;

    private String description;

    private double price;

    private byte[] image;

    private List<SizeDto> sizes;

    private Long categoryId;
}
