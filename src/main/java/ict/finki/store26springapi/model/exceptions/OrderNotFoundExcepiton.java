package ict.finki.store26springapi.model.exceptions;

public class OrderNotFoundExcepiton extends RuntimeException{
    public OrderNotFoundExcepiton(Long id) {
        super(String.format("Order with id %d was not found", id));
    }
}
