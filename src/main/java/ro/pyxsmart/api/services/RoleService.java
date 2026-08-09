package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.modelDTO.RolePermissionDTO;

public interface RoleService {

    Role createRole(Role role);

     void addPermission(RolePermissionDTO rolePermission);
}
