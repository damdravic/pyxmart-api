package ro.pyxsmart.api.repositories.queries;

public class UserQueries {

    public static final String INSERT_NEW_USER_QUERY = " INSERT INTO users (firstname, lastname, email, password, type) VALUES (:firstname, :lastname, :email, :password, :userType)" ;
    public static final String SELECT_USER_BY_EMAIL= "SELECT * FROM users WHERE email = :email";
    public static final String SELECT_USER_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM users WHERE email = :email";
    public static final String SELECT_AUTHORITIES_BY_USER_ID =" SELECT CONCAT('ROLE_',r.role_name) AS authority " +
            "FROM user_roles ur " +
            "JOIN roles r ON r.id = ur.role_id " +
            "WHERE ur.user_id = :userId " +
            "UNION " +
            "SELECT rp.permission AS authority " +
            "FROM user_roles ur " +
            "JOIN roles r ON r.id = ur.role_id " +
            "JOIN role_permissions rp ON rp.role_id = r.id " +
            "WHERE ur.user_id = :userId ";

}
