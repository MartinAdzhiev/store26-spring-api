package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.Order;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.CreditCardDto;
import ict.finki.store26springapi.model.exceptions.CreditCardNotFoundException;
import ict.finki.store26springapi.model.exceptions.UserNotFoundException;
import ict.finki.store26springapi.repository.CreditCardRepository;
import ict.finki.store26springapi.repository.UserRepository;
import ict.finki.store26springapi.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final UserRepository userRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<CreditCard> findById(Long id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public List<CreditCard> findAllByUser(Long userId) {
        System.out.println(creditCardRepository.findAllByUserId(userId));
        return creditCardRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<CreditCard> save(Long userId, CreditCardDto creditCardDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        CreditCard creditCard = new CreditCard(user,
                creditCardDto.getCardNumber(),
                creditCardDto.getCvv(),
                creditCardDto.getHolderName());

        return Optional.of(creditCardRepository.save(creditCard));
    }

    @Override
    public Optional<CreditCard> edit(Long id, CreditCardDto creditCardDto) {
        CreditCard creditCard = this.findById(id)
                .orElseThrow(() -> new CreditCardNotFoundException(id));

        creditCard.setCardNumber(creditCardDto.getCardNumber());
        creditCard.setCvv(creditCardDto.getCvv());
        creditCard.setHolderName(creditCardDto.getHolderName());

        return Optional.of(creditCardRepository.save(creditCard));
    }


    @Override
    public void deleteById(Long id) {
        creditCardRepository.deleteById(id);
    }
}
