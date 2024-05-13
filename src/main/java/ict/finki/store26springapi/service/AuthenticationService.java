package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.*;

public interface AuthenticationService {

    User register(RegisterRequest registerRequest);

    JwtAuthenticationResponse logIn(LogInRequest logInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    UserInfoResponse userInfo (String token);
}
