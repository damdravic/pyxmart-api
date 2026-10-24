package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.exceptions.AlreadyExistsException;
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

        if(categoryRepository.existsBySlug(category.getSlug())) {
            throw new AlreadyExistsException("Category slug already was used.Try another one !!!");
        }

        if(!existsByNameAndParentId(category.getName(),category.getParentId())) {

            return categoryRepository.createCategory(category);
        }else{
            throw new AlreadyExistsException("Category name exist");
        }
    }

    private boolean existsByNameAndParentIdAndIdNot(String name,Long parentId, Long id) {
       return categoryRepository.existByNameAndParentIdAndIdNot(name,parentId,id);
    }

    private boolean existsByNameAndParentId(String name,Long parentId) {

        return categoryRepository.existByNameAndParentId(name,parentId);
    }

    @Override
    public Category editCategory(Category category) {

        if(categoryRepository.existsBySlug(category.getSlug())) {
            throw new AlreadyExistsException("Category slug already was used.Try another one !!!");
        }

        if(!existsByNameAndParentIdAndIdNot(category.getName(),category.getParentId(),category.getId())){
            return categoryRepository.editCategory(category);
        }else{
            throw new AlreadyExistsException("Category name already exists");
        }
    }



    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public List<Category> getActiveCategories() {
        return categoryRepository.getActiveCategories();
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
