package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.enums.Gender;
import ict.finki.store26springapi.model.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    Long id;

    private String name;

    private Gender gender;

    private String description;

    private double price;

    private String color;

    private Long categoryId;

    private List<Size> sizes;

    private byte[] byteImage;

    private MultipartFile image;

}
