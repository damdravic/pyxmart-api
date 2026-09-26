package ro.pyxsmart.api.utils.mappers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import ro.pyxsmart.api.models.modelDTO.PycUserDetails;

import java.net.http.HttpRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.stream;

@Service
public class JwtTokenService {


    private static final String AUTHORITIES = "authorities";

    @Value(value = "${jwt.secret}")
    private String secret;
    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 300_000_000 ;

    public String generateAccessToken(PycUserDetails pycUser){
        String[] authorities = getAuthoritiesFromUser(pycUser);
        String[] names = getNamesFromUser(pycUser);
        return JWT.create().withIssuer("Pyxmart")
                .withAudience("")
                .withIssuedAt(new Date())
                .withSubject(pycUser.getUsername())
                .withArrayClaim(AUTHORITIES,authorities)
                .withArrayClaim("names",names)
                .withExpiresAt(new Date(currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes()));
                
    }

    public UsernamePasswordAuthenticationToken generateAuthToken(String email, List<GrantedAuthority> authorities, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email,null,authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    private String[] getNamesFromUser(PycUserDetails pycUser) {
        return new String[]{
                pycUser.getUser().getFirstname()
        };
    }

    private String[] getAuthoritiesFromUser(PycUserDetails pycUser) {
        return pycUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }

    public boolean isTokenValid(String token){
        JWTVerifier verifier = getVerifier();
        return !isTokenExpired(verifier,token);
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration  = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());

    }

    private JWTVerifier getVerifier() {
        JWTVerifier jwtVerifier ;
        try{
            Algorithm algorithm = HMAC512(secret);
            jwtVerifier = JWT.require(algorithm).withIssuer("Pyxmart").build();

        }catch (JWTVerificationException ex){
            throw new JWTVerificationException("Verification token invalid");
        }
        return jwtVerifier;
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier jwtVerifier = getVerifier();
        return jwtVerifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    public List<GrantedAuthority> getAuthorities(String token){
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getSubject(String token, HttpServletRequest request){
        try{
            return getVerifier().verify(token).getSubject();
        }catch (InvalidClaimException ex){
            request.setAttribute("invalidClaims", ex.getMessage());
            throw ex;
        }catch(TokenExpiredException ex ){
            request.setAttribute("expiredToken" , ex.getMessage());
            throw ex;
        }
    }



}
