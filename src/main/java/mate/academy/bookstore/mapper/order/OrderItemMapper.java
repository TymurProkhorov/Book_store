package mate.academy.bookstore.mapper.order;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.orderitem.impl")
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "item.book.id")
    OrderItemResponseDto toDto(OrderItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "price", source = "book.price")
    OrderItem toOrderItem(CartItem cartItem, Book book);
}
