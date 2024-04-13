package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.dto.CreditCardDto;
import ict.finki.store26springapi.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/creditCard/{id}")
    public ResponseEntity<CreditCard> findById(@PathVariable Long id) {
        return this.creditCardService.findById(id)
                .map(creditCard -> ResponseEntity.ok(creditCard))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/creditCard/all")
    public List<CreditCard> findAllByUser(@PathVariable Long userId) {
        return this.creditCardService.findAllByUser(userId);
    }



    @PostMapping("/{userId}/creditCard/add")
    public ResponseEntity<CreditCard> save(@PathVariable Long userId, @RequestBody CreditCardDto creditCardDto) {
        return this.creditCardService.save(userId, creditCardDto)
                .map(creditCard -> ResponseEntity.ok(creditCard))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/creditCard/{id}/edit")
    public ResponseEntity<CreditCard> edit(@PathVariable Long id, @RequestBody CreditCardDto creditCardDto) {
        return this.creditCardService.edit(id, creditCardDto)
                .map(creditCard -> ResponseEntity.ok().body(creditCard))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/creditCard/{id}/delete")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.creditCardService.deleteById(id);

        if (this.creditCardService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }



}
