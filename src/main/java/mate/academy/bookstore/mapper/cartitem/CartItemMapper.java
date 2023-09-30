package mate.academy.bookstore.mapper.cartitem;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.shoppingcart.request.CreateBookItemDto;
import mate.academy.bookstore.dto.shoppingcart.response.CartItemResponseDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.cartitem.impl")
@RequiredArgsConstructor
public abstract class CartItemMapper {
    @Mapping(target = "bookId", source = "cartItem.book.id")
    @Mapping(target = "bookTitle", source = "cartItem.book.title")
    public abstract CartItemResponseDto toDto(CartItem cartItem);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract CartItem toModel(CreateBookItemDto requestDto, Book book,
                                     ShoppingCart shoppingCart);
}
