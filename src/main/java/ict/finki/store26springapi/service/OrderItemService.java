package ict.finki.store26springapi.service;

import ict.finki.store26springapi.model.OrderItem;
import ict.finki.store26springapi.model.dto.CartItemDto;
import ict.finki.store26springapi.model.dto.OrderItemDto;

import java.util.List;


public interface OrderItemService {

    public List<OrderItemDto> getAllOrderItemsByOrderId(Long orderId);
    public OrderItemDto getDto(OrderItem orderItem);
}
