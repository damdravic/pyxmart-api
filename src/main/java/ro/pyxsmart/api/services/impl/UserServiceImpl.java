package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.mappers.UserDTOMappers;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;

import ro.pyxsmart.api.models.modelDTO.PycUserDetails;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserDTO;

import ro.pyxsmart.api.repositories.UserRepository;
import ro.pyxsmart.api.services.UserService;
import ro.pyxsmart.api.utils.mappers.JwtTokenService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final UserDTOMappers userDTOMappers;

    @Override
    public UserDTO create(RegisterUserDTO regUser, UserType userType) {
        return userRepository.create(regUser, userType);
    }

    @Override
    public User getUserByEmail(String email){

        return userRepository.getUserByEmail(email);
    }

    @Override
    public String generateToken(User user) {
        PycUserDetails pycUserDetails = new PycUserDetails(user, getAuthoritiesByUser(user));
        return jwtTokenService.generateAccessToken(pycUserDetails);
    }

    @Override
    public UserDTO getUserDTOFromUser(User user) {
        return userDTOMappers.getUserDtoFroUser(user);
    }

    @Override
    public List<GrantedAuthority> getAuthoritiesByUser(User user) {
        return userRepository.getAuthoritiesByUser(user);
    }
}
