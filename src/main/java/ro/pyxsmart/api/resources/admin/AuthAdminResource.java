package ro.pyxsmart.api.resources.admin;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.UserType;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserResponseDTO;
import ro.pyxsmart.api.services.UserService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthAdminResource {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register (@RequestBody @NotNull RegisterUserDTO regUser){

      UserResponseDTO userResponseDTO = userService.create(regUser, UserType.ADMIN);

        return ResponseEntity.ok().body(
                ApiResponse.<UserResponseDTO>builder()
                        .status(HttpStatus.OK.value())
                        .data(Map.of("userResponseDTO",userResponseDTO))
                        .message("User created")
                        .build()
        );

    }




}
