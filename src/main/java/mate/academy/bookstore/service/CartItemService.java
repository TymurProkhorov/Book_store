package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.shoppingcart.request.CreateBookItemDto;
import mate.academy.bookstore.model.CartItem;

public interface CartItemService {
    CartItem save(CreateBookItemDto requestDto);

    CartItem findById(Long id);

    void deleteById(Long cartItemId);
}
