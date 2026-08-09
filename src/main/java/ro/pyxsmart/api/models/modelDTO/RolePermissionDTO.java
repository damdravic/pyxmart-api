package ro.pyxsmart.api.models.modelDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolePermissionDTO {
    private String roleName;
    private String permission;
}
