package ict.finki.store26springapi.repository;

import ict.finki.store26springapi.model.Product;
import ict.finki.store26springapi.model.Review;
import ict.finki.store26springapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProduct(Product product);
    List<Review> findAllByUser(User user);
}
