package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.model.OrderItem;

public interface OrderItemService {
    OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId, Long itemId);

    OrderItem save(OrderItem orderItem);
}
