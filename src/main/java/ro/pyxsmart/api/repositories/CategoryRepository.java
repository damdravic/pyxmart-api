package ro.pyxsmart.api.repositories;

import ro.pyxsmart.api.models.Category;

import java.util.List;

public interface CategoryRepository {

    Category createCategory(Category category);

    Category editCategory( Category category);

    List<Category> getAllCategories();

    Category getCategoryById (Long id);

    Category getCategoryByParentAndName(Long parentId,String name);

    Category getCategoryByName(String name);

    Category getCategoryBySlug ( String slug);

    List<Category> getActiveCategories ();


    boolean existsByName(String name);
}
