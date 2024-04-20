package ict.finki.store26springapi.repository;

import ict.finki.store26springapi.model.CartItem;
import ict.finki.store26springapi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
