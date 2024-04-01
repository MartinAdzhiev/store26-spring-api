package ict.finki.store26springapi.repository;

import ict.finki.store26springapi.enums.Role;
import ict.finki.store26springapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
    User findByRole(Role role);
}
