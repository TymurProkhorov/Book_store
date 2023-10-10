package mate.academy.bookstore.repository.cart;

import mate.academy.bookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @EntityGraph(attributePaths = "cartItems")
    Optional<ShoppingCart> findById(Long id);
}
