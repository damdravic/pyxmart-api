package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.Category;

public interface CategoryService {

    Category createCategory(Category category);

    Category editCategory(Category category);

    Category[] getCategories();

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);
}
