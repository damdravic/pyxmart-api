package ro.pyxsmart.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ProviderManagerConfig {

    private final PasswordEncoder passwordEncoder;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService uds,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(uds);
        dao.setPasswordEncoder(passwordEncoder);
        return dao;
    }



    @Bean
    public ProviderManager providerManager(DaoAuthenticationProvider dao){
        return  new ProviderManager(dao);

    }

}
