package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.enums.Role;
import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.Order;
import ict.finki.store26springapi.model.Review;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Long shoppingCart;

    private List<Long> reviews;

    private List<CreditCard> creditCards;

    private List<Long> orders;
}
