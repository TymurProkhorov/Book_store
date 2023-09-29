package mate.academy.bookstore.mapper.category;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.category.impl")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
