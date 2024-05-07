package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.Size;
import ict.finki.store26springapi.model.dto.CreditCardDto;
import ict.finki.store26springapi.model.dto.SizeDto;

import java.util.List;
import java.util.Optional;

public interface SizeService {

    Optional<Size> findById(Long id);

    List<Size> findAllByProduct(Long productId);

    Optional<Size> save(Long productId, SizeDto sizeDto);

    Optional<Size> edit(Long id, SizeDto sizeDto);

    void deleteById(Long id);
}
