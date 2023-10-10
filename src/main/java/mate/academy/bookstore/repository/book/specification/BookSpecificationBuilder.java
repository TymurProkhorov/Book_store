package mate.academy.bookstore.repository.book.specification;

import lombok.AllArgsConstructor;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.SpecificationBuilder;
import mate.academy.bookstore.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);
        if (searchParameters.getTitles() != null
                && searchParameters.getTitles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("title")
                    .getSpecification(searchParameters.getTitles()));
        }
        if (searchParameters.getAuthors() != null
                && searchParameters.getAuthors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("author")
                    .getSpecification(searchParameters.getAuthors()));
        }
        if (searchParameters.getDescriptions() != null
                && searchParameters.getDescriptions().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("description")
                    .getSpecification(searchParameters.getDescriptions()));
        }
        if (searchParameters.getPrices() != null
                && searchParameters.getPrices().length > 0) {
            spec = spec.and(bookSpecificationProviderManager
                    .getSpecificationProvider("price")
                    .getSpecification(searchParameters.getPrices()));
        }
        return spec;
    }
}
