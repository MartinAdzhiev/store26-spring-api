package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.dto.UserInfoResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    UserInfoResponse userInfo(String email);
}
