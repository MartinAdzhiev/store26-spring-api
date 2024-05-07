package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private Long id;
    private int quantity;
    private Long shoppingCartId;
    private Long sizeId;
    private String sizeName;
    private ProductCartItemResponse productCartItemResponse;
}
