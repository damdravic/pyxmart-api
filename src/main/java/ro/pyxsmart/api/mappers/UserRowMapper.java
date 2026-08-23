package ro.pyxsmart.api.mappers;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class UserRowMapper implements RowMapper<User> {
    @Override
    public @Nullable User mapRow(ResultSet rs, int rowNum) throws SQLException {

       return User.builder()
               .id(rs.getLong("id"))
               .firstname(rs.getString("firstname"))
               .lastname(rs.getString("lastname"))
               .email(rs.getString("email"))
               .phone(rs.getString("phone"))
               .password(rs.getString("password"))
               .type(rs.getString("type"))
               .enabled(rs.getBoolean("enabled"))
               .emailVerified(rs.getBoolean("email_verified"))
               .accountLocked(rs.getBoolean("account_locked"))
               .failedLoginAttempts(rs.getInt("failed_login_attempts"))
               .lastLoginAt(toLocalDate(rs,"last_login_at"))
               .createdAt(toLocalDate(rs,"created_at"))
               .updatedAt(toLocalDate(rs,"updated_at"))
               .newsletterSubscribed(rs.getBoolean("newsletter_subscribed"))
               .termsAcceptedAt(toLocalDate(rs,"terms_accepted_at"))
               .build();


    }

    private LocalDate toLocalDate(ResultSet rs,String column) throws SQLException {
        Date date = rs.getDate(column);
        return date != null ? date.toLocalDate() : null;
    }


}
