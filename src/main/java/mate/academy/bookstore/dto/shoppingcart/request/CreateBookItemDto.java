package mate.academy.bookstore.dto.shoppingcart.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CreateBookItemDto {
    @Min(0)
    private Long bookId;
    @Min(0)
    private Integer quantity;
}
