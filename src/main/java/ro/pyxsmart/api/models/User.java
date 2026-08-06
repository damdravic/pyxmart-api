package ro.pyxsmart.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @NotNull
    private Long id;
    @NotEmpty(message = "First name cannot be empty")
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private String type;
    private boolean enabled;
    private boolean emailVerified;
    private boolean accountLocked;
    private int failedLoginAttempts;
    private LocalDate lastLoginAt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean newsletterSubscribed;
    private LocalDate termsAcceptedAt;






}
