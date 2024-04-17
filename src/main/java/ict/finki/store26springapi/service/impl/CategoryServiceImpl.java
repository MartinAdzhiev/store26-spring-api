package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.Category;
import ict.finki.store26springapi.model.dto.CategoryDto;
import ict.finki.store26springapi.model.exceptions.CategoryNotFoundException;
import ict.finki.store26springapi.repository.CategoryRepository;
import ict.finki.store26springapi.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> save(String name, String description) {
        Category category = new Category(name, description);

        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public Optional<Category> save(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName(), categoryDto.getDescription());

        return Optional.of(this.categoryRepository.save(category));    }

    @Override
    public Optional<Category> edit(Long id, String name, String description) {
        Category category = this.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(name);
        category.setDescription(description);

        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public Optional<Category> edit(Long id, CategoryDto categoryDto) {
        Category category = this.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
