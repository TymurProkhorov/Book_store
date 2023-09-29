package mate.academy.bookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.category.CategoryDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.category.CategoryMapper;
import mate.academy.bookstore.model.Category;
import mate.academy.bookstore.repository.category.CategoryRepository;
import mate.academy.bookstore.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(parseSortOrder(sort)));
        final List<CategoryDto> categoryDtos = categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
        return new PageImpl<>(categoryDtos,pageable, categoryDtos.size());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find category by id " + id)));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        final Category category = categoryMapper.toEntity(categoryDto);
        final Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (categoryRepository.existsById(id)) {
            Category category = new Category();
            category.setId(id);
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            final Category savedCategory = categoryRepository.save(category);
            return categoryMapper.toDto(savedCategory);
        }
        throw new EntityNotFoundException("Category by id " + id + " doesn't exist");
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    private Sort.Order parseSortOrder(String sort) {
        String[] parts = sort.split(",");
        String property = parts[0];
        String direction = parts[1].toUpperCase();
        return new Sort.Order(Sort.Direction.valueOf(direction), property);
    }
}
