package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.enums.Role;
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

        if (user.getRole().equals(Role.USER)) {
            userInfoResponse.setShoppingCart(user.getShoppingCart().getId());
        }
        return userInfoResponse;
    }
}
