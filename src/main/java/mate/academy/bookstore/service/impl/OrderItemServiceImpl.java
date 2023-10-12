package mate.academy.bookstore.service.impl;

import lombok.AllArgsConstructor;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.OrderItemMapper;
import mate.academy.bookstore.model.OrderItem;
import mate.academy.bookstore.repository.order.OrderItemRepository;
import mate.academy.bookstore.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderItemResponseDto findOrderItemByOrderIdAndId(Long orderId, Long itemId) {
        return orderItemMapper.toDto(orderItemRepository
                .findOrderItemByOrderIdAndId(orderId, itemId).orElseThrow(() ->
                        new EntityNotFoundException("can't find item with order id: "
                                + orderId + " and item id: " + itemId)));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
