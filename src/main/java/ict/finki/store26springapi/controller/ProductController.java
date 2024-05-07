package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.Size;
import ict.finki.store26springapi.model.dto.ProductDto;
import ict.finki.store26springapi.model.dto.SizeDto;
import ict.finki.store26springapi.service.ProductService;
import ict.finki.store26springapi.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productDtos = productService.findAll();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        return this.productService.findById(id)
                .map(product -> ResponseEntity.ok(productService.getDto(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/admin/product/add")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        return this.productService.save(productDto)
                .map(productDto1 -> ResponseEntity.ok(productDto1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PutMapping("/admin/product/{id}/edit")
    public ResponseEntity<ProductDto> editProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) throws IOException {
        return this.productService.edit(id, productDto)
                .map(productDto1 -> ResponseEntity.ok(productDto1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/admin/product/{id}/delete")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);

        if (this.productService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
