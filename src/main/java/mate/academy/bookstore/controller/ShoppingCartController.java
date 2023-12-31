package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shoppingcart.AddCartItemRequestDto;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.dto.shoppingcart.UpdateBookQuantityInCartDto;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ShoppingCart management", description = "endpoints for managing ShoppingCarts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(summary = "Get shopping cart")
    public ShoppingCartResponseDto getCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add book to shopping cart")
    public ShoppingCartResponseDto addBookToCart(
            @RequestBody @Valid AddCartItemRequestDto requestDto) {
        return shoppingCartService.addCartItem(requestDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update quantity of book in shopping cart")
    public ShoppingCartResponseDto updateQuantityOfBook(@PathVariable Long cartItemId,
            @RequestBody @Valid UpdateBookQuantityInCartDto requestDto) {
        return shoppingCartService.updateQuantityOfBooks(cartItemId, requestDto);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book from shopping cart")
    public ShoppingCartResponseDto deleteCartItem(@PathVariable Long cartItemId) {
        return shoppingCartService.deleteCartItem(cartItemId);
    }
}
