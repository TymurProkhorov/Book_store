package mate.academy.bookstore.mapper;

import java.math.BigDecimal;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.Order;
import mate.academy.bookstore.model.ShoppingCart;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.order.impl")
public interface OrderMapper {

    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "userId", source = "order.user.id")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "status", expression = "java(mate.academy.bookstore.model.Status.PENDING)")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())")
    Order toOrderFromCart(ShoppingCart shoppingCart);

    @AfterMapping
    default void setOrderTotal(@MappingTarget Order order, ShoppingCart shoppingCart) {
        order.setTotal(getTotal(shoppingCart));
    }

    private BigDecimal getTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream()
                .map(CartItem::getBook)
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
