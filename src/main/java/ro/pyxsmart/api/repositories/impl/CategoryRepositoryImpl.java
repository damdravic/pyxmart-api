package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.mappers.CategoryRowMapper;
import ro.pyxsmart.api.models.Category;
import ro.pyxsmart.api.repositories.CategoryRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.CategoryQueries.*;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    public final NamedParameterJdbcTemplate jdbc;


    @Override
    public Category createCategory(Category category) {

        KeyHolder kh = new GeneratedKeyHolder();

        SqlParameterSource params = getParams(category);
        jdbc.update(CREATE_CATEGORY_QUERY,params,kh);

        category.setId(Objects.requireNonNull(kh.getKey()).longValue());
        return category;
    }

    private SqlParameterSource getParams(Category category) {
        return new MapSqlParameterSource()
                .addValue("parentId", category.getParentId())
                .addValue("name",category.getName())
                .addValue("description",category.getDescription())
                .addValue("longDescription",category.getLongDescription())
                .addValue("slug",category.getSlug())
                .addValue("sortOrder",category.getSortOrder());

    }

    @Override
    public Category editCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbc.query(SELECT_ALL_CATEGORIES_QUERY,new CategoryRowMapper());
    }

    @Override
    public Category getCategoryById(Long id) {
        return jdbc.queryForObject(SELECT_CATEGORY_BY_ID_QUERY, Map.of("id", id), new CategoryRowMapper());
    }

    @Override
    public Category getCategoryByParentAndName(Long parentId, String name) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("name", name).addValue("parentId",parentId);
        return jdbc.queryForObject(SELECT_CATEGORY_BY_PARENT_AND_NAME_QUERY,params,new CategoryRowMapper());
    }

    @Override
    public Category getCategoryByName(String name) {
        return jdbc.queryForObject(SELECT_CATEGORY_BY_NAME_QUERY,Map.of("name",name),new CategoryRowMapper());
    }

    @Override
    public Category getCategoryBySlug(String slug) {
        return null;
    }

    @Override
    public List<Category> getActiveCategories() {
        return List.of();
    }

    @Override
    public boolean existsByName(String name) {
        return Boolean.TRUE.equals(
                jdbc.queryForObject(EXISTS_CATEGORY_BY_NAME_QUERY,Map.of("name",name),Boolean.class)
        );
    }
}
