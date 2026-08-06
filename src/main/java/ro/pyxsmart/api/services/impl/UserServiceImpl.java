package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.Role;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserResponseDTO;
import ro.pyxsmart.api.repositories.UserRepository;
import ro.pyxsmart.api.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO create(RegisterUserDTO regUser, UserType userType) {
        return userRepository.create(regUser, userType);
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
