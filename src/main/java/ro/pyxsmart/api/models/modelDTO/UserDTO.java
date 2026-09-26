package ro.pyxsmart.api.models.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String type;
    private boolean isUsingMFA;
}
