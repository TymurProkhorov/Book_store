package mate.academy.bookstore.repository.category;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :id")
    List<Book> getBooksByCategoriesId(Long id);

    @EntityGraph(attributePaths = "books")
    Optional<Category> findById(Long id);
}
