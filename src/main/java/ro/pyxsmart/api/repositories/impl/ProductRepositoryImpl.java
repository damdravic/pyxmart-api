package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.mappers.ProductRowMapper;
import ro.pyxsmart.api.models.Product;
import ro.pyxsmart.api.models.ProductStatus;
import ro.pyxsmart.api.repositories.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.ProductQueries.*;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {


    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Product createProduct(Product product) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = getParam(product);

        jdbc.update(INSERT_NEW_PRODUCT_QUERY,param,keyHolder);

        product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return product;
    }

    private SqlParameterSource getParam(Product product) {
        return  new MapSqlParameterSource()
                .addValue("id",product.getId())
                .addValue("name",product.getName())
                .addValue("code",product.getCode())
                .addValue("slug", product.getSlug())
                .addValue("shortDescription",product.getShortDescription())
                .addValue("description", product.getDescription())
                .addValue("categoryId", product.getCategoryId())
                .addValue("brandId", product.getBrandId())
                .addValue("productStatus", ProductStatus.DRAFT.name());

    }

    @Override
    public List<Product> getAllProducts() {
        return jdbc.query(SELECT_ALL_PRODUCTS_QUERY, new ProductRowMapper());
    }

    @Override
    public Product getProductById(Long id) {
        return jdbc.queryForObject(SELECT_PRODUCT_BY_ID_QUERY, Map.of("id",id),new ProductRowMapper()
        );
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        SqlParameterSource params = getParam(product);
       int rows = jdbc.update(UPDATE_PRODUCT_BY_ID_QUERY,params);

       if(rows == 0 ){
           return null;
           //TODO - throw exception
       }
       return product;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
