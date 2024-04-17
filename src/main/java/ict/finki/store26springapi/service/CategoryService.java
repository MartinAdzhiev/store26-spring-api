package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.Category;
import ict.finki.store26springapi.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Optional<Category> save(String name, String description);

    Optional<Category> save(CategoryDto countryDto);

    Optional<Category> edit(Long id, String name, String continent);

    Optional<Category> edit(Long id, CategoryDto countryDto);

    void deleteById(Long id);
}
