package ict.finki.store26springapi.model.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private int quantity;
    private Long sizeId;
    private String sizeName;
    private ProductOrderItemResponse productOrderItemResponse;
}
