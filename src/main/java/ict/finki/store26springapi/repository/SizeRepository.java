package ict.finki.store26springapi.repository;

import ict.finki.store26springapi.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    List<Size> findAllByProductId(Long productId);
}
