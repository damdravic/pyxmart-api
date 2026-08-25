package ro.pyxsmart.api.repositories.queries;

public class UserQueries {

    public static final String INSERT_NEW_USER_QUERY = " INSERT INTO users (firstname, lastname, email, password, type) VALUES (:firstname, :lastname, :email, :password, :userType)" ;
    public static final String SELECT_USER_BY_EMAIL= "SELECT * FROM users WHERE email = :email";
    public static final String SELECT_USER_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM users WHERE email = :email";
    public static final String SELECT_AUTHORITIES_BY_USER_ID ="";

}
