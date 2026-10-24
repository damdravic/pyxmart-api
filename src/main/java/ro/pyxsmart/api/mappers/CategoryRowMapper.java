package ro.pyxsmart.api.mappers;

import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .id(rs.getLong("id"))
                .parentId(rs.getLong("parent_id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .longDescription(rs.getString("long_description"))
                .slug(rs.getString("slug"))
                .sortOrder(rs.getInt("sort_order"))
                .active(rs.getBoolean("is_active"))
                .build();
    }
}
