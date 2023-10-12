package mate.academy.bookstore.repository.order;

import java.util.List;
import mate.academy.bookstore.model.Order;
import mate.academy.bookstore.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByUser(Pageable pageable, User user);
}
