package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.exceptions.AlreadyExistsException;
import ro.pyxsmart.api.mappers.UserDTOMappers;
import ro.pyxsmart.api.mappers.UserRowMapper;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.UserType;
import ro.pyxsmart.api.models.modelDTO.PycUserDetails;
import ro.pyxsmart.api.models.modelDTO.RegisterUserDTO;
import ro.pyxsmart.api.models.modelDTO.UserResponseDTO;
import ro.pyxsmart.api.repositories.UserRepository;

import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.UserQueries.*;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {


    private final NamedParameterJdbcTemplate jdbc;
    private final UserDTOMappers userDTOMapper;


    @Override
    public UserResponseDTO create(RegisterUserDTO regUser, UserType userType) {

        //1.Verify if user exist
      if(existsByEmail(regUser.getEmail())){
          throw new AlreadyExistsException(
                  "User already exists with email: " + regUser.getEmail()
          );
      }

        SqlParameterSource parameters =  getParameter(regUser, userType);
        KeyHolder kh = new GeneratedKeyHolder();

        try{
            jdbc.update(INSERT_NEW_USER_QUERY,parameters , kh);
            return userDTOMapper.getUserResponseDtoFroUser(
                       User.builder()
                      .id(Objects.requireNonNull(kh.getKey()).longValue())
                      .firstname(regUser.getFirstname())
                      .lastname(regUser.getLastname())
                      .email(regUser.getEmail())
                      .build()
            );

        }catch(DataAccessException dae){
             throw new IllegalStateException(dae.getMessage());
        }}


    private boolean existsByEmail(String email) {
        Integer count = jdbc.queryForObject(SELECT_USER_COUNT_BY_EMAIL,Map.of("email", email), Integer.class);
        return count != null && count > 0 ;
    }


    private SqlParameterSource getParameter(RegisterUserDTO regUser,UserType userType) {
        System.out.println("In PAram" + userType);
        return new MapSqlParameterSource()
                .addValue("firstname", regUser.getFirstname())
                .addValue("lastname",regUser.getLastname())
                .addValue("email",regUser.getEmail())
                .addValue("password",regUser.getPassword())
                .addValue("userType",userType.name());

    }

    @Override
    public UserDetails getUserByEmail(String email) {

        User user;
        try{
          user =   jdbc.queryForObject(SELECT_USER_BY_EMAIL, Map.of("email",email),new UserRowMapper());
        }catch (EmptyResultDataAccessException err){
            throw new UsernameNotFoundException("User not found");
        }
        return new PycUserDetails(user);
    }
}
