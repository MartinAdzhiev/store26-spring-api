package ict.finki.store26springapi.model;

import ict.finki.store26springapi.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String description;
    private double price;
    @Lob
    private byte[] image;

    @OneToMany(mappedBy = "product")
    private List<Size> sizes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
