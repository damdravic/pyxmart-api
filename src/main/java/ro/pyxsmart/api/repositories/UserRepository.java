package ro.pyxsmart.api.repositories;


import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;

import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserDTO;


public interface UserRepository {

    UserDTO create(RegisterUserDTO regUser, UserType userType);
    User getUserByEmail(String email);

}
