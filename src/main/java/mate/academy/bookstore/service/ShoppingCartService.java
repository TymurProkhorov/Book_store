package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.shoppingcart.AddCartItemRequestDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shoppingcart.UpdateBookQuantityInCartDto;
import mate.academy.bookstore.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartResponseDto getShoppingCart();

    ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto);

    ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                  UpdateBookQuantityInCartDto quantity);

    ShoppingCartResponseDto deleteCartItem(Long cartItemId);

    ShoppingCart getShoppingCartModel();
}
