package ro.pyxsmart.api.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.User;
import ro.pyxsmart.api.models.modelDTO.PycUserDetails;
import ro.pyxsmart.api.services.PycUserDetailsService;
import ro.pyxsmart.api.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PycUserDetailsServiceImpl implements PycUserDetailsService {

   private final UserService userService;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {

        User user = userService.getUserByEmail(email);
        List<GrantedAuthority> authorities = userService.getAuthoritiesByUser(user);

        return new PycUserDetails(user,authorities);

    }
}
