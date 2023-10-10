package mate.academy.bookstore.mapper.shoppingcart;

import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.shoppingcart.ShoppingCartResponseDto;
import mate.academy.bookstore.mapper.cartitem.CartItemMapper;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.service.UserService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.shoppingcart.impl")
public abstract class ShoppingCartMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemMapper cartItemMapper;

    public abstract ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);

    @AfterMapping
    public void setBookInfoToCartDto(@MappingTarget ShoppingCartResponseDto responseDto,
                                     ShoppingCart shoppingCart) {
        responseDto.setCartItems(shoppingCart.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    public void setUserToShoppingCartDto(@MappingTarget ShoppingCartResponseDto cartResponseDto) {
        cartResponseDto.setUserId(userService.getAuthenticatedUser().getId());
    }
}
