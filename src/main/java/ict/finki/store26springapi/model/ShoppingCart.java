package ict.finki.store26springapi.model;

import ict.finki.store26springapi.enums.ShoppingCartStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    private List<CartItem> cartItems = new ArrayList<>();
}
