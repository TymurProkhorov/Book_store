package mate.academy.bookstore.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.order.request.CreateOrderRequestDto;
import mate.academy.bookstore.dto.order.request.UpdateOrderStatusRequestDto;
import mate.academy.bookstore.dto.order.response.OrderItemsResponseDto;
import mate.academy.bookstore.dto.order.response.OrderResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.order.OrderItemMapper;
import mate.academy.bookstore.mapper.order.OrderMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.Order;
import mate.academy.bookstore.model.OrderItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.repository.order.OrderRepository;
import mate.academy.bookstore.service.OrderItemService;
import mate.academy.bookstore.service.OrderService;
import mate.academy.bookstore.service.ShoppingCartService;
import mate.academy.bookstore.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;

    @Override
    public OrderResponseDto createOrder(CreateOrderRequestDto requestDto) {
        ShoppingCart userCart = shoppingCartService.getCart();
        Order orderFromCart = orderMapper.createOrderFromCart(userCart);
        orderFromCart.setShippingAddress(requestDto.getShippingAddress());
        orderFromCart.setTotal(getTotal(userCart));
        Order savedOrder = orderRepository.save(orderFromCart);

        Set<OrderItem> orderItems = getOrdersFromUserCart(userCart);
        orderItems.forEach(item -> item.setOrder(savedOrder));
        orderItems.forEach(orderItemService::save);
        orderFromCart.setOrderItems(orderItems);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrderHistory(Pageable pageable) {
        return orderRepository.getAllByUser(userService.getAuthenticatedUser()).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public void updateOrderStatus(
            Long orderId,
            UpdateOrderStatusRequestDto requestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Can't find order by id: " + orderId));
        order.setStatus(requestDto.getStatus());
        orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemsResponseDto> getOrderItems(Long orderId) {
        return orderRepository.findById(orderId).get().getOrderItems().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemsResponseDto getOrderItem(Long orderId, Long itemId) {
        return orderItemMapper.toDto(orderItemService.findByOrderAndId(orderId, itemId));
    }

    private BigDecimal getTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(CartItem::getBook)
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<OrderItem> getOrdersFromUserCart(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(item -> orderItemMapper.toOrderItem(item, item.getBook()))
                .collect(Collectors.toSet());
    }
}
