package ict.finki.store26springapi.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super(String.format("User with id %d was not found", id));
    }
}
