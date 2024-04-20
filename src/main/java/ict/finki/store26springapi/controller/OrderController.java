package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.Order;
import ict.finki.store26springapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        return this.orderService.findById(orderId)
                .map(order -> ResponseEntity.ok(order)).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> userOrders = orderService.findByUserId(userId);
        if (!userOrders.isEmpty()) {
            return ResponseEntity.ok(userOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/{userId}/create")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId) {
        return this.orderService.save(userId)
                .map(order -> ResponseEntity.ok().body(order))
                .orElse(ResponseEntity.badRequest().build());
    }
}
