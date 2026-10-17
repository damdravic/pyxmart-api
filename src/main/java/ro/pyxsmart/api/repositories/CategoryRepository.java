package ro.pyxsmart.api.repositories;

import ro.pyxsmart.api.models.Category;

public interface CategoryRepository {

    Category createCategory(Category category);

    Category editCategory( Category category);


}
