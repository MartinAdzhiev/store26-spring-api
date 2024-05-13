package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.*;
import ict.finki.store26springapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LogInRequest logInRequest) {
        return ResponseEntity.ok(authenticationService.logIn(logInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponse> userInfo (@RequestHeader(value="Authorization") String bearerToken) {
        return ResponseEntity.ok(authenticationService.userInfo(bearerToken));
    }


}
