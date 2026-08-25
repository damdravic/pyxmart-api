package ro.pyxsmart.api.config.filters;

import com.auth0.jwt.exceptions.InvalidClaimException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.pyxsmart.api.utils.mappers.JwtTokenService;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){

        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            return true;
        }

        return isPublic(request);

    }

    private boolean isPublic(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/admin/login")
                || path.startsWith("/admin/register")
                || path.equals("/admin/verify/code")
                || path.equals("/admin/verify/token");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
try{
        //GET TOKEN
        String token = null;
        String path = request.getServletPath();

        if (path.startsWith("/shop/")) {
            token = resolveShopToken(request);
        } else if (path.startsWith("/admin/")) {
            token = resolveAdminToken(request);
        }

        //CHECK IF TOKEN IS NULL
        if (token == null || token.isBlank()) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }

        //We have the token...check if it is valid
        if (jwtTokenService.isTokenValid(token)) {
            String subject = jwtTokenService.getSubject(token, request);
            List<GrantedAuthority> authorityList = jwtTokenService.getAuthorities(token);
            log.info("subject -> {}" , subject);
            log.info("Authority List -> {}" , authorityList);
            Authentication authentication = jwtTokenService.generateAuthToken(subject, authorityList, request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);

    }catch (InvalidClaimException ex ){
    SecurityContextHolder.clearContext();
    request.setAttribute("invalidClaims", ex.getMessage());
    throw ex;
    }




    }

    private String resolveAdminToken(HttpServletRequest request) {
        if(request.getCookies() != null ){
            for(Cookie c : request.getCookies()){
                if("adminAccessToken".equals(c.getName())){
                    log.info(c.getValue());
                    return c.getValue();
                }
            }
        }
        return null;
    }

    private String resolveShopToken(HttpServletRequest request) {

        if(request.getCookies() != null){
            for(Cookie c : request.getCookies()){
                if("customerAccessToken".equals(c.getName())){
                    log.info("customerAccessToken founded");
                    return c.getValue();
                }
            }
        }
        return null;
    }

}
