package ro.pyxsmart.api.mappers;


import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ro.pyxsmart.api.models.ProductFinancial;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductFinancialRowMapper implements RowMapper<ProductFinancial> {
    @Override
    public @Nullable ProductFinancial mapRow(ResultSet rs, int rowNum) throws SQLException {
        return
                ProductFinancial.builder()
                .id(rs.getLong("id"))
                .productId(rs.getLong("product_id"))
                .purchasePrice(rs.getBigDecimal("purchase_price"))
                .sellingPrice(rs.getBigDecimal("selling_price"))
                .build();
    }
}
