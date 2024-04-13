package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.UserInfoResponse;
import ict.finki.store26springapi.repository.UserRepository;
import ict.finki.store26springapi.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
       userInfoResponse.setShoppingCart(user.getShoppingCart().getId());

       List<Long> creditCards = user.getCreditCards().stream()
               .map(creditCard -> creditCard.getId())
               .collect(Collectors.toList());

       userInfoResponse.setCreditCards(creditCards);

       List<Long> reviews = user.getReviews().stream()
               .map(review -> review.getId())
               .collect(Collectors.toList());
       userInfoResponse.setReviews(reviews);

       List<Long> orders = user.getOrders().stream()
               .map(order -> order.getId())
               .collect(Collectors.toList());
       userInfoResponse.setOrders(orders);


       return userInfoResponse;
    }
}
