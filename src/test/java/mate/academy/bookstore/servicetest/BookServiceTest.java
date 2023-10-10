package mate.academy.bookstore.servicetest;

import mate.academy.bookstore.dto.book.BookDto;
import mate.academy.bookstore.mapper.book.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.service.BookService;
import mate.academy.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookServiceImpl;

//    @Test
//    public void findAll_WithValidBooks_ShouldReturnListOfBooks() {
//        Pageable pageable = Pageable.ofSize(1);
//        List<Book> books = createBookList();
//        Mockito.when(bookRepository.findAll(pageable)).thenReturn(books);
//        int actual = bookServiceImpl.findAll(pageable).size();
//        Assertions.assertNotEquals(2, actual);
//    }

    private List<Book> createBookList() {
        Book book1 = new Book();
        book1.setAuthor("author1");
        book1.setId(1L);
        book1.setCategories(new HashSet<>());
        book1.setIsbn("2121105");
        book1.setPrice(BigDecimal.valueOf(100));
        book1.setTitle("title1");
        book1.setCoverImage("cover image1");
        book1.setDeleted(false);
        book1.setDescription("descr1");

        Book book2 = new Book();
        book2.setAuthor("author2");
        book2.setId(1L);
        book2.setCategories(new HashSet<>());
        book2.setIsbn("2121106");
        book2.setPrice(BigDecimal.valueOf(200));
        book2.setTitle("title2");
        book2.setCoverImage("cover image2");
        book2.setDeleted(false);
        book2.setDescription("descr2");
        return List.of(book1, book2);
    }
}
