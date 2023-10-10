package mate.academy.bookstore.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shoppingcart.CreateBookItemDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.cartitem.CartItemRepository;
import mate.academy.bookstore.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.bookstore.service.CartItemService;
import mate.academy.bookstore.service.ShoppingCartService;
import mate.academy.bookstore.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemService cartItemService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserService userService;
    private final CartItemRepository cartItemRepository;

    @Override
    public ShoppingCartResponseDto getUserCart() {
        User user = userService.getAuthenticatedUser();
        return shoppingCartMapper
                .toDto(shoppingCartRepository.findShoppingCartByUserId(user.getId()));
    }

    @Override
    public ShoppingCart getCart() {
        User authenticatedUser = userService.getAuthenticatedUser();
        return shoppingCartRepository.findShoppingCartByUserId(authenticatedUser.getId());
    }

    @Override
    public ShoppingCartResponseDto addBookToUserCart(CreateBookItemDto requestDto) {
        ShoppingCart shoppingCart = getShoppingCartByUser();
        Long bookIdToAdd = requestDto.getBookId();
        Integer quantityToAdd = requestDto.getQuantity();

        Optional<CartItem> item = shoppingCart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(bookIdToAdd))
                .findFirst();

        if (item.isPresent()) {
            CartItem existingCartItem = item.get();
            int newQuantity = existingCartItem.getQuantity() + quantityToAdd;
            existingCartItem.setQuantity(newQuantity);
            return getUserCart();
        }
        CartItem cartItem = cartItemService.save(requestDto);
        shoppingCart.getCartItems().add(cartItem);
        return getUserCart();
    }

    @Override
    public ShoppingCartResponseDto updateBookQuantity(Long id, UpdateBookQuantityDto requestDto) {
        cartItemService.findById(id).setQuantity(requestDto.getQuantity());
        return getUserCart();
    }

    @Override
    public ShoppingCartResponseDto removeBookFromCart(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("can't delete cart item by id: " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
        return getUserCart();
    }

    public ShoppingCart getShoppingCartByUser() {
        User user = userService.getAuthenticatedUser();
        return shoppingCartRepository.findShoppingCartByUserId(user.getId());
    }
}
