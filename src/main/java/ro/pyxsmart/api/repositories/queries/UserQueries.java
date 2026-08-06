package ro.pyxsmart.api.repositories.queries;

public class UserQueries {

    public static final String INSERT_NEW_USER_QUERY = " INSERT INTO user (firstname, lastname, email, password, type) VALUES (:firstname, :lastname, :email, :password, :userType)" ;


}
