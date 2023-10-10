package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.shoppingcart.request.CreateBookItemDto;
import mate.academy.bookstore.dto.shoppingcart.request.UpdateBookQuantityDto;
import mate.academy.bookstore.dto.shoppingcart.response.ShoppingCartResponseDto;
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

    @PostMapping
    @Operation(summary = "Add book to shopping cart",
            description = "Adding the book to shopping cart")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCartResponseDto addBookToShoppingCart(
            @RequestBody @Valid CreateBookItemDto request) {
        return shoppingCartService.addBookToUserCart(request);
    }

    @Operation(summary = "get user`s shopping cart",
            description = "getting user`s shopping cart")
    @GetMapping
    public ShoppingCartResponseDto getUserCart() {
        return shoppingCartService.getUserCart();
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update book quantity",
            description = "Updating book quantity in user's shopping cart")
    public ShoppingCartResponseDto updateShoppingCart(
            @PathVariable Long cartItemId,
            @RequestBody @Valid UpdateBookQuantityDto requestDto) {
        return shoppingCartService.updateBookQuantity(cartItemId, requestDto);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book", description = "Deleting book by id")
    public ShoppingCartResponseDto deleteBookFromCart(@PathVariable Long cartItemId) {
        return shoppingCartService.removeBookFromCart(cartItemId);
    }
}
