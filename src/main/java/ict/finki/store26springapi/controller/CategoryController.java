package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.Category;
import ict.finki.store26springapi.model.dto.CategoryDto;
import ict.finki.store26springapi.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category/all")
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/user/category/all")
    public List<Category> findAllUser() {
        return this.categoryService.findAll();
    }

    @GetMapping("/admin/category/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return this.categoryService.findById(id)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/admin/category/add")
    public ResponseEntity<Category> save(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.save(categoryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/admin/category/{id}/edit")
    public ResponseEntity<Category> edit(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return this.categoryService.edit(id, categoryDto)
                .map(category -> ResponseEntity.ok().body(category))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/admin/category/{id}/delete")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.categoryService.deleteById(id);

        if (this.categoryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
