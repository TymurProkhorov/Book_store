package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shoppingcart.AddCartItemRequestDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shoppingcart.UpdateBookQuantityInCartDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.CartItemMapper;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.repository.cart.CartItemRepository;
import mate.academy.bookstore.repository.cart.ShoppingCartRepository;
import mate.academy.bookstore.service.ShoppingCartService;
import mate.academy.bookstore.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartResponseDto getShoppingCart() {
        return shoppingCartMapper.toDto(getShoppingCartModel());
    }

    @Override
    public ShoppingCartResponseDto addCartItem(AddCartItemRequestDto requestDto) {
        ShoppingCart cart = getShoppingCartModel();
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(() ->
                new EntityNotFoundException("can't find book by id: " + requestDto.getBookId()));
        CartItem cartItem = cartItemMapper.toModel(requestDto, book);
        cartItem.setShoppingCart(cart);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.addCartItem(savedCartItem);
        return getShoppingCart();
    }

    @Transactional
    @Override
    public ShoppingCartResponseDto updateQuantityOfBooks(Long cartItemId,
                                                         UpdateBookQuantityInCartDto requestDto) {
        cartItemRepository.findById(cartItemId).orElseThrow(() ->
                        new EntityNotFoundException("can't find cart item by id: " + cartItemId))
                .setQuantity(requestDto.quantity());
        return getShoppingCart();
    }

    @Override
    public ShoppingCartResponseDto deleteCartItem(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new EntityNotFoundException("can't delete cart item by id: " + cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
        return getShoppingCart();
    }

    @Override
    public ShoppingCart getShoppingCartModel() {
        User user = userService.getUser();
        return shoppingCartRepository.findById(user.getId()).get();
    }
}
