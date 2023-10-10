package mate.academy.bookstore.mapper.book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.book.impl")
public interface BookMapper {
    @Mapping(target = "categoriesIds", source = "categories", qualifiedByName = "mapCategoriesToIds")
    BookResponseDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Book toEntity(CreateBookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @Named("mapCategoriesToIds")
    default List<Long> mapCategoriesToIds(Set<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }
}
