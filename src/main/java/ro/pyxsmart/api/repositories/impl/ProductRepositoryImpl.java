package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.exceptions.AlreadyExistsException;
import ro.pyxsmart.api.mappers.ProductAdminDTORowMapper;
import ro.pyxsmart.api.mappers.ProductRowMapper;
import ro.pyxsmart.api.models.*;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;
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
    public Product createProduct(ProductRequest productRequest) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = getParam(productRequest);
        jdbc.update(INSERT_NEW_PRODUCT_QUERY, param, keyHolder);
        return getProductById(Objects.requireNonNull(keyHolder.getKey()).longValue());


    }

    private SqlParameterSource getParam(ProductRequest productRequest) {
        return new MapSqlParameterSource()
                // .addValue("id",productRequest.getId())
                .addValue("name", productRequest.getName())
                .addValue("code", productRequest.getMpnCode())
                .addValue("slug", productRequest.getSlug())
                .addValue("shortDescription", productRequest.getShortDescription())
                .addValue("description", productRequest.getDescription())
                .addValue("categoryId", productRequest.getCategoryId())
                .addValue("brandId", productRequest.getBrandId())
                .addValue("productStatus", ProductStatus.DRAFT.name())
                .addValue("featured", productRequest.getFeatured());

    }


    @Override
    public List<ProductAdminDTO> getAllProducts() {

        return jdbc.query(SELECT_ALL_PRODUCTS_ADMIN_QUERY, new ProductAdminDTORowMapper()
        );
    }

    @Override
    public Product getProductById(Long id) {
        return jdbc.queryForObject(SELECT_PRODUCT_BY_ID_QUERY, Map.of("id", id), new ProductRowMapper()
        );
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }


    @Override
    public void verifyCodeAndSlug(String code, String slug) {
        Integer countCode = jdbc.queryForObject(SELECT_COUNT_PRODUCT_BY_CODE_QUERY, Map.of("code", code), Integer.class);
        Integer countSlug = jdbc.queryForObject(SELECT_COUNT_PRODUCT_BY_SLUG_QUERY, Map.of("slug", slug), Integer.class);

        if (countCode != null && countCode > 0) {
            throw new AlreadyExistsException("This code already exist !!!");
        }

        if (countSlug != null && countSlug > 0) {
            throw new AlreadyExistsException("This slug already exist !!!");
        }

    }

    @Override
    public ProductImages createProductImage(ProductImages productImage) {

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(INSERT_NEW_PRODUCT_IMAGE_QUERY, getProdImagParams(productImage));

        productImage.setId(Objects.requireNonNull(kh.getKey()).longValue());
        return productImage;
    }

    private MapSqlParameterSource getProdImagParams(ProductImages productImage) {
        return new MapSqlParameterSource()
                .addValue("productId",productImage.getProductId())
                .addValue("image_url",productImage.getImageUrl())
                .addValue("altText",productImage.getAltText())
                .addValue("sortOrder",productImage.getSortOrder())
                .addValue("isPrimary",productImage.getPrimary());

    }

    @Override
    public void deleteProductImagesByProductId(Long productId) {

        jdbc.update(DELETE_PRODUCT_IMAGE_BY_PRODUCT_ID_QUERY,Map.of("productId",productId));

    }
}
