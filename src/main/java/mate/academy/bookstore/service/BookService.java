package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto requestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto getBookById(Long id);

    void deleteById(Long id);

    BookResponseDto update(Long id, CreateBookRequestDto requestDto);

    List<BookResponseDto> search(BookSearchParameters searchParameters, Pageable pageable);

    Page<BookDtoWithoutCategoryIds> findAllByCategory(Long id, int page, int size, String sort);
}
