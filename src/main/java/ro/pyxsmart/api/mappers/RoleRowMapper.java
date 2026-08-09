package ro.pyxsmart.api.mappers;

import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {


    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

        Role role = new Role();
        role.setId(rs.getLong("id"));
        role.setName(rs.getString("role_name"));
        // TODO set list of permissions


        return role;




    }
}
