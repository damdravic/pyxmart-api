package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.modelDTO.RolePermissionDTO;
import ro.pyxsmart.api.repositories.RoleRepository;
import ro.pyxsmart.api.services.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role createRole(Role role) {
        return roleRepository.createRole(role);
    }

    @Override
    public void addPermission(RolePermissionDTO rolePermission) {
        roleRepository.addPermission(rolePermission);
    }
}
