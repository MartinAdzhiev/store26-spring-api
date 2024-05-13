package ict.finki.store26springapi.model.exceptions;

public class CreditCardNotFoundException extends RuntimeException{
    public CreditCardNotFoundException(Long id) {
        super(String.format("Credit Card with id %d was not found", id));
    }
}
