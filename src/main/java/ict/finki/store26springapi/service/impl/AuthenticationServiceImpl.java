package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.enums.Role;
import ict.finki.store26springapi.model.ShoppingCart;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.*;
import ict.finki.store26springapi.repository.UserRepository;
import ict.finki.store26springapi.service.AuthenticationService;
import ict.finki.store26springapi.service.JwtService;
import ict.finki.store26springapi.service.ShoppingCartService;
import ict.finki.store26springapi.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final UserService userService;

    private final ShoppingCartService shoppingCartService;



    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JwtService jwtService,
                                     UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    public User register(RegisterRequest registerRequest) {
        User user = new User();

        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));


        User savedUser = userRepository.save(user);
        ShoppingCart shoppingCart = shoppingCartService.save(savedUser.getId()).orElseThrow();

        return savedUser;
    }

    public JwtAuthenticationResponse logIn(LogInRequest logInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getEmail(), logInRequest.getPassword()));

        var user = userRepository.findByEmail(logInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }

    public UserInfoResponse userInfo (String bearerToken) {
        String token = bearerToken.split(" ")[1];
        String userEmail = jwtService.extractUsername(token);


        return userService.userInfo(userEmail);
    }
}
