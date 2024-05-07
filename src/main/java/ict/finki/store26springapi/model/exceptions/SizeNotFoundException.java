package ict.finki.store26springapi.model.exceptions;

public class SizeNotFoundException extends RuntimeException{
    public SizeNotFoundException(Long id) {
        super(String.format("Size with id %d was not found", id));
    }
}
