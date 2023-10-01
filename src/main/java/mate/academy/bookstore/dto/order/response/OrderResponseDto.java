package mate.academy.bookstore.dto.order.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;
import mate.academy.bookstore.model.Order;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemsResponseDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Order.Status status;
}
