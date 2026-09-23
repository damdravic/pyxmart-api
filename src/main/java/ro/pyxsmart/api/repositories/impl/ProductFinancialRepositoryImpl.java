package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.mappers.ProductFinancialMapper;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.repositories.ProductFinancialRepository;

import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.ProductQueries.INSERT_PRODUCT_FINANCIAL_QUERY;
import static ro.pyxsmart.api.repositories.queries.ProductQueries.SELECT_PRODUCT_FINANCIAL_BY_PRODUCT_ID_QUERY;

@Repository
@RequiredArgsConstructor
public class ProductFinancialRepositoryImpl implements ProductFinancialRepository {

    private final NamedParameterJdbcTemplate jdbc;


    @Override
    public ProductFinancial createProdFinancial(ProductFinancial productFinancial) {

        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = getSqlParams(productFinancial);

        jdbc.update(INSERT_PRODUCT_FINANCIAL_QUERY,parameterSource,kh);

        productFinancial.setId(Objects.requireNonNull(kh.getKey()).longValue());
        return productFinancial;


    }

    private SqlParameterSource getSqlParams(ProductFinancial productFinancial) {
        return new MapSqlParameterSource()
                .addValue("id",productFinancial.getId())
                .addValue("productId", productFinancial.getProductId())
                .addValue("purchasePrice", productFinancial.getPurchasePrice())
                .addValue("sellingPrice",productFinancial.getSellingPrice())
                .addValue("vatRateId", productFinancial.getVatRateId());
    }

    @Override
    public ProductFinancial getProdFinancialByProductId(Long productId) {
       return  jdbc.queryForObject(SELECT_PRODUCT_FINANCIAL_BY_PRODUCT_ID_QUERY, Map.of("productId",productId), new ProductFinancialMapper());
    }

    @Override
    public ProductFinancial updateProdFinancialByProductId(Long productId) {
        return null;
    }
}
