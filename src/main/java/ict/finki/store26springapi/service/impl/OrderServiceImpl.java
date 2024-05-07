package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.enums.OrderStatus;
import ict.finki.store26springapi.model.*;
import ict.finki.store26springapi.model.dto.OrderDto;
import ict.finki.store26springapi.model.exceptions.CreditCardNotFoundException;
import ict.finki.store26springapi.model.exceptions.OrderNotFoundExcepiton;
import ict.finki.store26springapi.model.exceptions.UserNotFoundException;
import ict.finki.store26springapi.repository.*;
import ict.finki.store26springapi.service.OrderItemService;
import ict.finki.store26springapi.service.OrderService;
import ict.finki.store26springapi.service.SizeService;
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

    private final CreditCardRepository creditCardRepository;

    private final SizeService sizeService;

    private final OrderItemService orderItemService;



    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, CartItemRepository cartItemRepository, OrderItemRepository orderItemRepository, CreditCardRepository creditCardRepository, SizeService sizeService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.creditCardRepository = creditCardRepository;
        this.sizeService = sizeService;
        this.orderItemService = orderItemService;
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
    public Optional<Order> save(Long userId, OrderDto orderDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUser_Id(userId);

        List<CartItem> cartItems = cartItemRepository.findAllByShoppingCart_Id(shoppingCart.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Shopping cart is empty");
        }

        CreditCard creditCard = creditCardRepository.findById(orderDto.getCreditCardId())
                .orElseThrow(() -> new CreditCardNotFoundException(orderDto.getCreditCardId()));

        Order order = new Order();
        order.setStatus(OrderStatus.PROCESSING);
        order.setUser(user);
        order.setTime(LocalDateTime.now());
        order.setCard(creditCard);
        order.setAddress(orderDto.getAddress());
        order.setPhoneNumber(orderDto.getPhoneNumber());

        Order savedOrder = orderRepository.save(order);

        cartItems.forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSize(cartItem.getSize());
            orderItem.setOrder(savedOrder);

            orderItemRepository.save(orderItem);

            sizeService.updateSize(orderItem.getSize().getId(), orderItem.getQuantity());

            cartItemRepository.deleteById(cartItem.getId());
        });

        savedOrder.setTotal(calculateOrderTotal(savedOrder.getId()));

        return Optional.of(orderRepository.save(savedOrder));
    }

    @Override
    public Optional<Order> updateStatus(Long orderId, OrderDto orderDto) {
        Order order = this.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundExcepiton(orderId));

        order.setStatus(orderDto.getStatus());

        return Optional.of(orderRepository.save(order));
    }


    @Override
    public Double calculateOrderTotal(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrder_Id(orderId);
        double total = 0.0;

      for (OrderItem orderItem : orderItems) {
          total += orderItem.getProduct().getPrice() * orderItem.getQuantity();
      }

        return total;
    }

    @Override
    public OrderDto getDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setTime(order.getTime());
        orderDto.setPhoneNumber(order.getPhoneNumber());
        orderDto.setCard(order.getCard());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotal(order.getTotal());

        orderDto.setOrderItems(orderItemService.getAllOrderItemsByOrderId(order.getId()));

        return orderDto;
    }


}
