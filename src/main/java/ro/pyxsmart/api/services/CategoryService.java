package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category editCategory(Category category);

    List<Category> getAllCategories();

    List<Category> getActiveCategories();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);
}
