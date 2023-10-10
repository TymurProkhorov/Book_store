package mate.academy.bookstore.dto.shoppingcart;

import jakarta.validation.constraints.Positive;

public record UpdateBookQuantityInCartDto(@Positive int quantity) {
}
