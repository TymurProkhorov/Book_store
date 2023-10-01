package mate.academy.bookstore.service;

import mate.academy.bookstore.model.OrderItem;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem);

    OrderItem findByOrderAndId(Long orderId, Long id);
}
