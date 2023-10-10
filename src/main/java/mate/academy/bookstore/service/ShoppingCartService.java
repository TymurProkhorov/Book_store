package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.shoppingcart.request.CreateBookItemDto;
import mate.academy.bookstore.dto.shoppingcart.request.UpdateBookQuantityDto;
import mate.academy.bookstore.dto.shoppingcart.response.ShoppingCartResponseDto;
import mate.academy.bookstore.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getUserCart();

    ShoppingCart getCart();

    ShoppingCartResponseDto addBookToUserCart(CreateBookItemDto requestDto);

    ShoppingCartResponseDto updateBookQuantity(Long id, UpdateBookQuantityDto requestDto);

    ShoppingCartResponseDto removeBookFromCart(Long cartItemId);
}
