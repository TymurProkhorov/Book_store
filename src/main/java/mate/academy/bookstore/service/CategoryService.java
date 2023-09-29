package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.category.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Page<CategoryDto> findAll(int page, int size, String sort);

    CategoryDto getById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);

}
