package ict.finki.store26springapi.service.impl;

import ict.finki.store26springapi.model.OrderItem;
import ict.finki.store26springapi.model.dto.OrderItemDto;
import ict.finki.store26springapi.repository.OrderItemRepository;
import ict.finki.store26springapi.service.OrderItemService;
import ict.finki.store26springapi.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public List<OrderItemDto> getAllOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = this.orderItemRepository.findAllByOrder_Id(orderId);
        return orderItems.stream().map(orderItem -> this.getDto(orderItem))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto getDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductOrderItemResponse(productService.getProductInOrderItem(orderItem.getProduct()));
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setSizeId(orderItem.getSize().getId());
        orderItemDto.setSizeName(orderItem.getSize().getName());

        return orderItemDto;
    }

}
