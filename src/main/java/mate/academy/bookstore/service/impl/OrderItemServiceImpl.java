package mate.academy.bookstore.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.model.OrderItem;
import mate.academy.bookstore.repository.order.OrderItemRepository;
import mate.academy.bookstore.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem findByOrderAndId(Long orderId, Long itemId) {
        return orderItemRepository
                .findOrderItemByOrderIdAndId(orderId, itemId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can't find order item by parameters: "
                                        + "OrderId:" + orderId + ",and itemId:" + itemId));
    }
}
