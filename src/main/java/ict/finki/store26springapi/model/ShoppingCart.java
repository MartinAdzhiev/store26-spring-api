package ict.finki.store26springapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public ShoppingCart(ShoppingCartStatus status, User user) {
        this.status = status;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    @OneToOne
    @JsonIgnore
    private User user;

//    @OneToMany(mappedBy = "shoppingCart")
//    private List<CartItem> cartItems = new ArrayList<>();
}
