package ict.finki.store26springapi.controller;

import ict.finki.store26springapi.model.Order;
import ict.finki.store26springapi.model.User;
import ict.finki.store26springapi.model.dto.OrderDto;
import ict.finki.store26springapi.service.AuthenticationService;
import ict.finki.store26springapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;
    private final AuthenticationService authenticationService;

    public OrderController(OrderService orderService, AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.findAll()
                .stream()
                .map(order -> orderService.getDto(order))
                .collect(Collectors.toList());

        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/order/{orderId}/updateStatus")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId,
                                                      @RequestBody OrderDto orderDto) {
        return this.orderService.updateStatus(orderId, orderDto)
                .map(order -> ResponseEntity.ok(orderService.getDto(order)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(value = {"/user/order/{orderId}",
                         "/admin/order/{orderId}"})
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        return this.orderService.findById(orderId)
                .map(order -> ResponseEntity.ok(orderService.getDto(order))).
                orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/orders")
    public ResponseEntity<List<OrderDto>> getOrdersByUserId(@RequestHeader(value = "Authorization") String bearerToken) {
        Long userId = authenticationService.userInfo(bearerToken).getId();

        List<OrderDto> userOrders = orderService.findByUserId(userId)
                .stream()
                .map(order -> orderService.getDto(order))
                .collect(Collectors.toList());

        if (!userOrders.isEmpty()) {
            return ResponseEntity.ok(userOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/order/create")
    public ResponseEntity<OrderDto> createOrder(@RequestHeader(value = "Authorization") String bearerToken,
                                                @RequestBody OrderDto orderDto) {

        Long userId = authenticationService.userInfo(bearerToken).getId();

        return this.orderService.save(userId, orderDto)
                .map(order -> ResponseEntity.ok().body(orderService.getDto(order)))
                .orElse(ResponseEntity.badRequest().build());
    }
}
