package mate.academy.bookstore.repository.book;

import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceMinSpecificationProvider implements SpecificationProvider<Book> {
    private static final String FIELD_NAME = "price";
    private static final String FILTER_KEY = "priceFrom";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(FIELD_NAME), params[0]);
    }
}
