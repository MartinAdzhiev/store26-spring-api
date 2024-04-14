package ict.finki.store26springapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
//    @ToString.Exclude
    private User user;

    private String cardNumber;

    private int cvv;

    private String holderName;

//    @OneToMany(mappedBy = "card")
//    @JsonIgnore
//    private List<Order> orders = new ArrayList<>();

    public CreditCard(User user, String cardNumber, int cvv, String holderName) {
        this.user = user;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.holderName = holderName;
    }
}
