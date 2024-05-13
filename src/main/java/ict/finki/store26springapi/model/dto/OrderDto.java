package ict.finki.store26springapi.model.dto;

import ict.finki.store26springapi.enums.OrderStatus;
import ict.finki.store26springapi.model.CreditCard;
import ict.finki.store26springapi.model.OrderItem;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime time;

    private Long creditCardId;

    private CreditCard card;

    private String address;

    private String phoneNumber;

    private Double total;

    List<OrderItemDto> orderItems;
}
