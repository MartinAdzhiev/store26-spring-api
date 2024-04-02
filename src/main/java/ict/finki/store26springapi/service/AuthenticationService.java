package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.JwtAuthenticationResponse;
import ict.finki.store26springapi.model.dto.LogInRequest;
import ict.finki.store26springapi.model.dto.RefreshTokenRequest;
import ict.finki.store26springapi.model.dto.RegisterRequest;

public interface AuthenticationService {

    User register(RegisterRequest registerRequest);

    JwtAuthenticationResponse logIn(LogInRequest logInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
