package mate.academy.bookstore.mapper.book;

import mate.academy.bookstore.config.MapperConfig;
import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class, implementationPackage = "<PACKAGE_NAME>.book.impl")
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
