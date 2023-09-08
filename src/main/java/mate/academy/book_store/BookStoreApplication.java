package mate.academy.book_store;

import mate.academy.book_store.model.Book;
import mate.academy.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

@SpringBootApplication
public class BookStoreApplication {
    BookService bookService;

    @Autowired
    public BookStoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book = new Book();
            book.setAuthor("Stephen King");
            book.setIsbn("1234");
            book.setPrice(BigDecimal.valueOf(1000));
            book.setDescription("Horror novel about monsters");
            book.setTitle("The mist");
            book.setCoverImage("coverImageUrl");

            bookService.save(book);
            System.out.println(bookService.findAll());
        };
    }
}
