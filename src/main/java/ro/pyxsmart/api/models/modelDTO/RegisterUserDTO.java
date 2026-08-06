package ro.pyxsmart.api.models.modelDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class RegisterUserDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
