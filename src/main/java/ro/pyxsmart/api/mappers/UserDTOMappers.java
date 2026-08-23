package ro.pyxsmart.api.mappers;

import org.springframework.stereotype.Component;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.modelDTO.UserDTO;


@Component
public class UserDTOMappers {


    public UserDTO getUserDtoFroUser(User user){
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .type(user.getType())
                .build();

    }


}
