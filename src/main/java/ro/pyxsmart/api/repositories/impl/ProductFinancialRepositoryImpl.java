package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.exceptions.NotFoundException;
import ro.pyxsmart.api.mappers.ProductFinancialRowMapper;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.models.modelDTO.ProductFinancialRequest;
import ro.pyxsmart.api.repositories.ProductFinancialRepository;

import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.ProductQueries.*;

@Repository
@RequiredArgsConstructor
public class ProductFinancialRepositoryImpl implements ProductFinancialRepository {

    private final NamedParameterJdbcTemplate jdbc;


    @Override
    public ProductFinancial createProductFinancial(Long productId, ProductFinancialRequest financial) {

        if (verifyVatId(financial.getVatRateId())){

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(INSERT_PRODUCT_FINANCIAL_QUERY, getFinancialParams(productId, financial), keyHolder);

            return ProductFinancial.builder()
                    .id(Objects.requireNonNull(keyHolder.getKey()).longValue())
                    .productId(productId)
                    .purchasePrice(financial.getPurchasePrice())
                    .sellingPrice(financial.getSellingPrice())
                    .vatRateId(financial.getVatRateId())
                    .build();
        } else {
            throw new NotFoundException("VAT Rate not exist !!!");
        }
    }

    @Override
    public ProductFinancial getProdFinancialByProductId(Long productId) {
        return null;
    }

    @Override
    public ProductFinancial updateProdFinancialByProductId(Long productId) {
        return null;
    }

    private boolean verifyVatId(Long vatRateId) {
        Integer count = jdbc.queryForObject(SELECT_PRODUCT_FINANCIAL_BY_VAT_ID_QUERY, Map.of("vatRateId", vatRateId), Integer.class);
        return count != null && count > 0;
    }

    private MapSqlParameterSource getFinancialParams(Long id, ProductFinancialRequest financial) {
        return new MapSqlParameterSource()
                .addValue("productId", id)
                .addValue("purchasePrice", financial.getPurchasePrice())
                .addValue("sellingPrice", financial.getSellingPrice())
                .addValue("vatRateId", financial.getVatRateId());
    }


}
