package ro.pyxsmart.api.repositories;


import org.springframework.security.core.GrantedAuthority;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;

import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserDTO;

import java.util.List;


public interface UserRepository {

    UserDTO create(RegisterUserDTO regUser, UserType userType);
    User getUserByEmail(String email);
    List<GrantedAuthority> getAuthoritiesByUser(User user);
}
