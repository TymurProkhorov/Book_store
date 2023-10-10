package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotBlank;
import mate.academy.bookstore.model.Status;

public record UpdateOrderStatusDto(@NotBlank Status status) {
}
