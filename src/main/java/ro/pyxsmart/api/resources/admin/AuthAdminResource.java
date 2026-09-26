package ro.pyxsmart.api.resources.admin;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import ro.pyxsmart.api.models.ApiResponse;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;
import ro.pyxsmart.api.models.modelDTO.Credentials;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserDTO;
import ro.pyxsmart.api.services.UserService;

import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
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

    @GetMapping("/logout")
    public ResponseEntity<@NonNull ApiResponse> logout(){
        ResponseCookie accessCookie = ResponseCookie.from("adminAccessToken","")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(Duration.ofMinutes(0))
                .build();



        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,accessCookie.toString())
                .body(
                       ApiResponse.builder()
                                .status(HttpStatus.OK.value())
                                .message("Logout success !!!")
                                .build());
    }


    @GetMapping("/authMe")
    public ResponseEntity<@NonNull ApiResponse> authMe(Authentication authentication){

        //String email =  Objects.requireNonNull(authentication.getPrincipal()).toString();
        String email = authentication.getName();
        UserDTO userDTO = userService.getUserDTOFromUser(userService.getUserByEmail(email));

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Authenticated user")
                        .data(Map.of("userDTO", userDTO))
                        .build()
        );

    }

    private ResponseEntity<@NonNull ApiResponse> sendResponse(User user) {

        String token = userService.generateToken(user);
        System.out.println("token generated " + token);

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
