package ro.pyxsmart.api.mappers;

import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductAdminDTORowMapper implements RowMapper<ProductAdminDTO> {


    @Override
    public ProductAdminDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return ProductAdminDTO.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .code(rs.getString("code"))
                .shortDescription(rs.getString("short_description"))
                .description(rs.getString("description"))
                .categoryId(rs.getLong("category_id"))
                .brandId(rs.getLong("brand_id"))
                .metaTitle(rs.getString("meta_title"))
                .metaDescription(rs.getString("meta_description"))
                .metaKeywords(rs.getString("meta_keywords"))
                .createdAt(rs.getTimestamp("created_at") != null
                         ?  rs.getTimestamp("created_at").toLocalDateTime()
                         : null )
                .updatedAt(rs.getTimestamp("updated_at") != null
                        ? rs.getTimestamp("updated_at").toLocalDateTime()
                        : null )
                .publishedAt(rs.getTimestamp("published_at") != null
                        ? rs.getTimestamp("published_at").toLocalDateTime()
                        : null)
                .purchasePrice(rs.getBigDecimal("purchase_price"))
                .sellingPrice(rs.getBigDecimal("selling_price"))
                .vatRateId(rs.getLong("vat_rate_id"))
                .build();
    }
}
