package ro.pyxsmart.api.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.modelDTO.PycUserDetails;
import ro.pyxsmart.api.services.PycUserDetailsService;
import ro.pyxsmart.api.services.UserService;

@Service
@RequiredArgsConstructor
public class PycUserDetailsServiceImpl implements PycUserDetailsService {

   private final UserService userService;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return new PycUserDetails(userService.getUserByEmail(email));

    }
}
