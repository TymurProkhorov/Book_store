package mate.academy.bookstore.mapper.user;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.user.response.UserResponseDto;
import mate.academy.bookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.user.impl")
public interface UserMapper {
    UserResponseDto toDto(User user);
}
