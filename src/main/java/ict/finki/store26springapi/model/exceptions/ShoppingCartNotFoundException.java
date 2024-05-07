package ict.finki.store26springapi.model.exceptions;

import ict.finki.store26springapi.model.ShoppingCart;

public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(Long id) {
        super(String.format("Shopping Cart with id %d was not found", id));
    }
}
