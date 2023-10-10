package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.request.CreateOrderRequestDto;
import mate.academy.bookstore.dto.order.request.UpdateOrderStatusRequestDto;
import mate.academy.bookstore.dto.order.response.OrderItemsResponseDto;
import mate.academy.bookstore.dto.order.response.OrderResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderRequestDto requestDto);

    List<OrderResponseDto> getOrderHistory(Pageable pageable);

    void updateOrderStatus(Long id, UpdateOrderStatusRequestDto requestDto);

    List<OrderItemsResponseDto> getOrderItems(Long orderId);

    OrderItemsResponseDto getOrderItem(Long orderId, Long itemId);
}
