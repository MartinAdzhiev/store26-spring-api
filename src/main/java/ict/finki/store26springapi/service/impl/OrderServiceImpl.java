package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.enums.OrderStatus;
import ict.finki.store26springapi.model.*;
import ict.finki.store26springapi.model.exceptions.UserNotFoundException;
import ict.finki.store26springapi.repository.*;
import ict.finki.store26springapi.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return this.orderRepository.findByUser_Id(userId);
    }


    @Override
    @Transactional
    public Optional<Order> save(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser_Id(userId);
        List<CartItem> cartItems = cartItemRepository.findAllByShoppingCart_Id(shoppingCart.getId());

        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setUser(user);
        order.setTime(LocalDateTime.now());
        //card?

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            //namaluvanje quantity?
            orderItem.setSize(cartItem.getSize());
            orderItem.setOrder(order);
            orderItemRepository.save(orderItem);

            cartItemRepository.delete(cartItem);
        }

        return Optional.of(orderRepository.save(order));
    }


    @Override
    public Optional<Double> calculateOrderTotal(Long orderId) {
//        Optional<Order> order = this.orderRepository.findById(orderId);
//        if (order.isPresent()) {
//            Double total = 0.0;
//            for (OrderItem orderItem : order.get().getOrderItems()) {
//                total += orderItem.getProduct().getPrice() * orderItem.getQuantity();
//            }
//            return Optional.of(total);
//        } else {
//            return Optional.empty();
//        }

        return Optional.empty();
    }
}
