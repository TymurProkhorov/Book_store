package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.request.CreateOrderRequestDto;
import mate.academy.bookstore.dto.order.request.UpdateOrderStatusRequestDto;
import mate.academy.bookstore.dto.order.response.OrderItemsResponseDto;
import mate.academy.bookstore.dto.order.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(CreateOrderRequestDto requestDto);

    List<OrderResponseDto> getUserOrders();

    OrderResponseDto updateOrderStatus(Long id, UpdateOrderStatusRequestDto requestDto);

    List<OrderItemsResponseDto> getOrderItems(Long orderId);

    OrderItemsResponseDto getOrderItem(Long orderId, Long itemId);
}
