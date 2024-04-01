package ict.finki.store26springapi;

import ict.finki.store26springapi.enums.Role;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Store26SpringApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(Store26SpringApiApplication.class, args);
    }


}
