package ro.pyxsmart.api.mappers;


import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandRowMapper implements RowMapper<Brand> {
    @Override
    public @Nullable Brand mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Brand.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .slug(rs.getString("slug"))
                .active(rs.getBoolean("active"))
                .logoUrl(rs.getString("logo_url"))
                .logoUrlSecondary(rs.getString("logo_url_secondary"))
                .createdAt(rs.getDate("created_at"))
                .updatedAt(rs.getDate("updated_at"))
                .build();
    }
}
