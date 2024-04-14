package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.enums.ShoppingCartStatus;
import ict.finki.store26springapi.model.ShoppingCart;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.exceptions.UserNotFoundException;
import ict.finki.store26springapi.repository.ShoppingCartRepository;
import ict.finki.store26springapi.repository.UserRepository;
import ict.finki.store26springapi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id);
    }

    @Override
    public Optional<ShoppingCart> findByUserId(Long userId) {
        return Optional.of(shoppingCartRepository.findByUser_Id(userId));
    }

    @Override
    public Optional<ShoppingCart> save(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        ShoppingCart shoppingCart = new ShoppingCart(ShoppingCartStatus.ACTIVE, user);

        return Optional.of(shoppingCartRepository.save(shoppingCart));
    }
}
