package mate.academy.bookstore.repositorycategory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.repository.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;
    private Category savedFictionCategory;
    private Category savedHorrorCategory;

    @BeforeEach
    void setUp() {
        savedFictionCategory = categoryRepository.save(createFictionCategory());
        Book savedMistBook = bookRepository.save(createMistBook());
        savedFictionCategory.addBook(savedMistBook);
        savedHorrorCategory = categoryRepository.save(createHorrorCategory());
        Book savedPetCemeteryBook = bookRepository.save(createPetCemeteryBook());
        savedHorrorCategory.addBook(savedPetCemeteryBook);
        savedHorrorCategory.addBook(savedMistBook);
    }

    @Test
    @DisplayName("Get all books by fiction category")
    void getBooksByCategoriesId_WhereIdIs1_ExpectedOneBook() {
        List<Book> actual = categoryRepository.getBooksByCategoriesId(savedFictionCategory.getId());
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Test
    @DisplayName("Get all books by horror category")
    void getBooksByCategoriesId_WhereIdIs2_ExpectedTwoBook() {
        List<Book> actual = categoryRepository.getBooksByCategoriesId(savedHorrorCategory.getId());
        assertNotNull(actual);
        assertEquals(2, actual.size());
    }

    @Test
    @DisplayName("Get empty list of books by non-existent category")
    void getBooksByCategoriesId_WhereIdIs1000_NotOk() {
        List<Book> actual = categoryRepository.getBooksByCategoriesId(1000L);
        assertNotNull(actual);
        assertEquals(0, actual.size());
    }

    private static Category createFictionCategory() {
        Category category = new Category();
        category.setName("Fiction");
        category.setDescription("Fiction books");
        return category;
    }

    private static Book createMistBook() {
        Book book = new Book();
        book.setDescription("story about people trapped in shop with monsters outside");
        book.setCoverImage("mist.jpg");
        book.setAuthor("Stephen King");
        book.setIsbn("1234567890");
        book.setTitle("The mist");
        book.setPrice(new BigDecimal("100.00"));
        return book;
    }

    private static Book createPetCemeteryBook() {
        Book book = new Book();
        book.setDescription("story about resurrectional cemetery");
        book.setCoverImage("PetCemetery.jpg");
        book.setAuthor("S.King");
        book.setIsbn("9876543210");
        book.setTitle("Pet cemetery");
        book.setPrice(new BigDecimal("50.00"));
        return book;
    }

    private static Category createHorrorCategory() {
        Category category = new Category();
        category.setName("Fiction");//!!!!!!!!!!!!!
        category.setDescription("Fiction books");
        return category;
    }
}