package ict.finki.store26springapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JsonBackReference
    private Size size;
}
