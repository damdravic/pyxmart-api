package ro.pyxsmart.api.mappers;

import org.jspecify.annotations.Nullable;
import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

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
               .lastLoginAt(rs.getDate("last_login_at").toLocalDate())
               .createdAt(rs.getDate("created_at").toLocalDate())
               .updatedAt(rs.getDate("updated_at").toLocalDate())
               .newsletterSubscribed(rs.getBoolean("newsletter_subscribed"))
               .termsAcceptedAt(rs.getDate("terms_accepted").toLocalDate())
               .build();


    }
}
