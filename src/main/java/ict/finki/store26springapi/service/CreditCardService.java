package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.dto.CreditCardDto;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {

    Optional<CreditCard> findById(Long id);

    List<CreditCard> findAllByUser(Long userId);

    Optional<CreditCard> save(Long userId, CreditCardDto creditCardDto);

    Optional<CreditCard> edit(Long id, CreditCardDto creditCardDto);

    void deleteById(Long id);
}
