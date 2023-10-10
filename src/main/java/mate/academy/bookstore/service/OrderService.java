package mate.academy.bookstore.service;

import java.util.List;

import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderRequestDto requestDto);

    List<OrderResponseDto> getOrderHistory(Pageable pageable);

    void updateOrderStatus(Long id, UpdateOrderStatusDto requestDto);

    List<OrderItemResponseDto> getOrderItems(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);
}
