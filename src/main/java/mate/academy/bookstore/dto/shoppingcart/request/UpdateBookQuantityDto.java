package mate.academy.bookstore.dto.shoppingcart.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateBookQuantityDto {
    @Min(0)
    private int quantity;
}
