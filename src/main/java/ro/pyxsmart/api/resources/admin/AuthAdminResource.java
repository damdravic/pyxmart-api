package ro.pyxsmart.api.resources.admin;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;
import ro.pyxsmart.api.models.modelDTO.Credentials;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserDTO;
import ro.pyxsmart.api.services.UserService;

import java.time.Duration;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AuthAdminResource {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity< @NonNull  ApiResponse> register (@RequestBody RegisterUserDTO regUser){
      UserDTO userResponseDTO = userService.create(regUser, UserType.ADMIN);
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .data(Map.of("userResponseDTO",userResponseDTO))
                        .message("User created")
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<@NonNull ApiResponse> login (@RequestBody Credentials credentials){

        User user = userService.getUserByEmail(credentials.getEmail());
        return user.isUsingMFA() ? sendCode(user) : sendResponse(user);

    }

    private ResponseEntity<@NonNull ApiResponse> sendResponse(User user) {

        String token = userService.generateToken(user);

        ResponseCookie accessCookie = ResponseCookie.from("adminAccessToken" , token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.ofMinutes(150))
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE , accessCookie.toString())
                .body(
                        ApiResponse.builder()
                                .status(HttpStatus.OK.value())
                                .message("Authentication success !!!")
                                .data(Map.of("userDTO",userService.getUserDTOFromUser(user)))
                                .build());
    }

    private ResponseEntity<@NonNull ApiResponse> sendCode(User user) {
        return null;
    }


}
