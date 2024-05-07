package ict.finki.store26springapi.model.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super(String.format("Product with id %d was not found", id));
    }
}
