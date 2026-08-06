package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserResponseDTO;

public interface UserService  {

    UserResponseDTO create(RegisterUserDTO regUser, UserType userType);
    //UserResponseDTO create(RegisterUserDTO regUser);
    User getUserByEmail(String email);

}
