package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.shoppingcart.CreateBookItemDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getUserCart();

    ShoppingCart getCart();

    ShoppingCartResponseDto addBookToUserCart(CreateBookItemDto requestDto);

    ShoppingCartResponseDto updateBookQuantity(Long id, UpdateBookQuantityDto requestDto);

    ShoppingCartResponseDto removeBookFromCart(Long cartItemId);
}
