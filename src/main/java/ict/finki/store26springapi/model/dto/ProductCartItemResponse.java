package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.enums.Gender;
import lombok.Data;

@Data
public class ProductCartItemResponse {
    Long id;

    private String name;

    private Gender gender;

    private double price;

    private String color;

    private byte[] byteImage;
}
