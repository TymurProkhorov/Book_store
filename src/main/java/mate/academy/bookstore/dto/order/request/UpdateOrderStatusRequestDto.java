package mate.academy.bookstore.dto.order.request;

import lombok.Data;
import mate.academy.bookstore.model.Order;

@Data
public class UpdateOrderStatusRequestDto {
    private Order.Status status;
}
