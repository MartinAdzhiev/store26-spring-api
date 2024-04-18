package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.Size;
import ict.finki.store26springapi.model.dto.SizeDto;
import ict.finki.store26springapi.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping("/admin/product/{productId}/size/add")
    public ResponseEntity<Size> addSize(@PathVariable Long productId, @RequestBody SizeDto sizeDto){
        return this.sizeService.save(productId, sizeDto)
                .map(size -> ResponseEntity.ok(size))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/admin/product/size/{id}/edit")
    public ResponseEntity<Size> editSize(@PathVariable Long id, @RequestBody SizeDto sizeDto){
        return this.sizeService.edit(id, sizeDto)
                .map(size -> ResponseEntity.ok(size))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
