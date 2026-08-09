package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.exceptions.AlreadyExistsException;
import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.modelDTO.RolePermissionDTO;
import ro.pyxsmart.api.repositories.RoleRepository;

import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.RoleQueries.*;

@RequiredArgsConstructor
@Repository
public class RoleRepositoryImpl implements RoleRepository {


    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role createRole(Role role) {

        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource().addValue("name",role.getName());

     try{
         jdbc.update(INSERT_NEW_ROLE_QUERY,param,kh);
     }catch (DuplicateKeyException ex){
         throw new AlreadyExistsException("Role" + role.getName() +  "name already exist ");
     }
      Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setId(Objects.requireNonNull(kh.getKey()).longValue());


        return newRole;
    }

    @Override
    public void addPermission(RolePermissionDTO rolePermission) {

        SqlParameterSource param = new MapSqlParameterSource()
                        .addValue("roleId",getRoleId(rolePermission.getRoleName()))
                        .addValue("permission", rolePermission.getPermission());

        jdbc.update(INSERT_PERMISSION_TO_ROLE_QUERY,param);

    }

    private Long getRoleId(String roleName) {
       return jdbc.queryForObject(SELECT_ROLE_ID_BY_NAME_QUERY, Map.of("roleName", roleName),Long.class);
    }


}
