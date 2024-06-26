package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.enums.Role;
import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.ShoppingCart;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.ReviewDto;
import ict.finki.store26springapi.model.dto.UserInfoResponse;
import ict.finki.store26springapi.repository.UserRepository;
import ict.finki.store26springapi.service.CreditCardService;
import ict.finki.store26springapi.service.ReviewService;
import ict.finki.store26springapi.service.ShoppingCartService;
import ict.finki.store26springapi.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ShoppingCartService shoppingCartService;

    private final CreditCardService creditCardService;

    private final ReviewService reviewService;

    public UserServiceImpl(UserRepository userRepository, ShoppingCartService shoppingCartService, CreditCardService creditCardService, ReviewService reviewService) {
        this.userRepository = userRepository;
        this.shoppingCartService = shoppingCartService;
        this.creditCardService = creditCardService;
        this.reviewService = reviewService;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public UserInfoResponse userInfo(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setId(user.getId());
        userInfoResponse.setFirstName(user.getFirstName());
        userInfoResponse.setLastName(user.getLastName());
        userInfoResponse.setEmail(user.getEmail());
        userInfoResponse.setRole(user.getRole());


        if (user.getRole().equals(Role.USER)) {
            Optional<ShoppingCart> shoppingCart = shoppingCartService.findByUserId(user.getId());
            userInfoResponse.setShoppingCart(shoppingCart.get().getId());

            List<CreditCard> creditCards = creditCardService.findAllByUser(user.getId());
            userInfoResponse.setCreditCards(creditCards);

            List<ReviewDto> reviews = reviewService.findAllByUserId(user.getId());
            userInfoResponse.setReviews(reviews);
        }

        return userInfoResponse;
    }
}
