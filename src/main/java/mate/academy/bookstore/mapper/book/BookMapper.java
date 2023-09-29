package mate.academy.bookstore.mapper.book;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.book.impl")
public interface BookMapper {
    BookDto toDto(Book book);

    @Mapping(target = "categories", ignore = true)
    Book toEntity(CreateBookRequestDto requestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        final Set<Long> categoryIds = book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
        bookDto.setCategoriesIds(categoryIds);
    }

    @AfterMapping
    default void setCategoriesIntoBook(@MappingTarget Book book, CreateBookRequestDto createDto) {
        final Set<Category> categories = new HashSet<>();
        for (Long id : createDto.getCategories()) {
            Category category = new Category(id);
            categories.add(category);
        }
        book.setCategories(categories);
    }
}
