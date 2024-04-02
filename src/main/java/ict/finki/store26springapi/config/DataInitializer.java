package ict.finki.store26springapi.config;

import ict.finki.store26springapi.enums.Role;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initAdmin() {
        User adminAccount = userRepository.findByRole(Role.ADMIN);

        if (adminAccount == null) {
            User user = new User();

            user.setEmail("admin@gmail.com");
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));

            userRepository.save(user);
        }
    }

}
