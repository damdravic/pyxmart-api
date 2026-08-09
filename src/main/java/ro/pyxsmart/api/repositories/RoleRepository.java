package ro.pyxsmart.api.repositories;

import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.modelDTO.RolePermissionDTO;

@Repository
public interface RoleRepository {

    Role createRole(Role role);

    void addPermission(RolePermissionDTO rolePermission);
}
