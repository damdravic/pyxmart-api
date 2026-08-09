package ro.pyxsmart.api.resources.admin;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.modelDTO.RolePermissionDTO;
import ro.pyxsmart.api.services.RoleService;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class RoleResources {
    private final RoleService roleService;

    @PostMapping("/addNewRole")
    public ResponseEntity<@NonNull ApiResponse> addNewRole(@RequestBody Role role){
        return  ResponseEntity.ok(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Role created successfully ")
                        .data(Map.of("role",roleService.createRole(role)))
                        .build()
        );
    }

    @PostMapping("/addPermission")
    public ResponseEntity<@NonNull ApiResponse> addPermission(@RequestBody RolePermissionDTO rolePermission){
        roleService.addPermission(rolePermission);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Permission added")
                        .build()
        );
    }


}
