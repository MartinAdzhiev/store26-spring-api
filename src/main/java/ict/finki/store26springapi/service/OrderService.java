package ict.finki.store26springapi.service;

import ict.finki.store26springapi.enums.OrderStatus;
import ict.finki.store26springapi.model.Order;
import ict.finki.store26springapi.model.OrderItem;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long userId);

    Optional<Order> save(Long userId);

    Optional<Double> calculateOrderTotal(Long orderId);



//    Optional<Order> updateOrderStatus(Long orderId, OrderStatus newStatus);
//
//    Optional<Order> addOrderItemToOrder(Long orderId, OrderItem orderItem);
//
//    Optional<Order> removeOrderItemFromOrder(Long orderId, Long orderItemId);
}
