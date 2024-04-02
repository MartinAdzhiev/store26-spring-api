package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Gender gender;
    private String description;
    private double price;
    private byte[] image;
    private List<SizeDTO> sizes;
    private Long categoryId;
}
