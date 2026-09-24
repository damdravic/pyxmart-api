package ro.pyxsmart.api.mappers;

import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.Product;
import ro.pyxsmart.api.models.ProductStatus;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setCode(rs.getString("code"));
        product.setShortDescription(rs.getString("short_description"));
        product.setDescription(rs.getString("description"));
        product.setCategoryId(rs.getLong("category_id"));
        product.setBrandId(rs.getLong("brand_id"));
        product.setStatus(ProductStatus.valueOf(rs.getString("product_status")));
        product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());



      return product;

    }
}
