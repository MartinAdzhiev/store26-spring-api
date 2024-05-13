package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.ShoppingCart;
import ict.finki.store26springapi.model.dto.CreditCardDto;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> findById(Long id);

    Optional<ShoppingCart> findByUserId(Long userId);

    Optional<ShoppingCart> save(Long userId);
}
