package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.models.Category;
import ro.pyxsmart.api.repositories.CategoryRepository;

import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.CategoryQueries.CREATE_CATEGORY_QUERY;

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
                .addValue("name",category.getName())
                .addValue("description",category.getDescription())
                .addValue("longDescription",category.getLongDescription())
                .addValue("categoryParentId",category.getCategoryParentId());
    }

    @Override
    public Category editCategory(Category category) {
        return null;
    }
}
