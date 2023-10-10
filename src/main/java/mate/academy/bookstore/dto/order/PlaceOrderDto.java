package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotBlank;

public record PlaceOrderDto(@NotBlank String shippingAddress) {
}
