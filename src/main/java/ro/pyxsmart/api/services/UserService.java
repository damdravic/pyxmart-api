package ro.pyxsmart.api.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;

import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserDTO;

import java.util.List;


public interface UserService  {

    UserDTO create(RegisterUserDTO regUser, UserType userType);

    User getUserByEmail(String email);

    String generateToken (User user);

    UserDTO getUserDTOFromUser(User user);


    List<GrantedAuthority> getAuthoritiesByUser(User user);
}
