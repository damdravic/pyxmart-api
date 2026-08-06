package ro.pyxsmart.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.pyxsmart.api.exceptions.CustomAccessDeniedHandler;
import ro.pyxsmart.api.exceptions.CustomAuthenticationEntryPoint;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {


    private static final String[] PUBLIC_URL = {"",""};
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());
        http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URL).permitAll());

        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint));
       // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }


    @Bean
    public AuthenticationManager authenticationManager(ProviderManager providerManager ){
        return providerManager ;

    }







}
