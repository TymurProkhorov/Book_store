package mate.academy.bookstore.repository;

import java.util.List;
import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();

    BookDto getBookById(Long id);
}
