package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.Category;
import ro.pyxsmart.api.repositories.CategoryRepository;
import ro.pyxsmart.api.services.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.createCategory(category);
    }

    @Override
    public Category editCategory(Category category) {
        return null;
    }

    @Override
    public Category[] getCategories() {
        return new Category[0];
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }
}
