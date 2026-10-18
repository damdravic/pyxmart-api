package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.Category;
import ro.pyxsmart.api.repositories.CategoryRepository;
import ro.pyxsmart.api.services.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {

        if(!existsByName(category.getName())) {
            //TODO if category name exists, check if exists with the same parentId.
            //
            return categoryRepository.createCategory(category);
        }else{
            throw new RuntimeException("Category name already exists");
        }
    }

    private boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public Category editCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public List<Category> getActiveCategories() {
        return List.of();
    }

    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }
}
