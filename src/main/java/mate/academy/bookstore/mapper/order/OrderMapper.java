package mate.academy.bookstore.mapper.order;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.order.response.OrderResponseDto;
import mate.academy.bookstore.model.Order;
import mate.academy.bookstore.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.order.impl")
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);

    Order createOrderFromCart(ShoppingCart shoppingCart);
}
