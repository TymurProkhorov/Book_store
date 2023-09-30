package mate.academy.bookstore.dto.order.response;

import lombok.Data;

@Data
public class OrderItemsResponseDto {
    private Long id;
    private Long bookId;
    private Integer quantity;
}
