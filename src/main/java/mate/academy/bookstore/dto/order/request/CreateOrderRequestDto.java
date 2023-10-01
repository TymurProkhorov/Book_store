package mate.academy.bookstore.dto.order.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    @NotNull
    private String shippingAddress;
}
