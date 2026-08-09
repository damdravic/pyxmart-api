package ro.pyxsmart.api.repositories.queries;

public class RoleQueries {

    public static final String INSERT_NEW_ROLE_QUERY = "INSERT INTO roles (role_name) VALUES (:name) ";
    public static final String INSERT_PERMISSION_TO_ROLE_QUERY = "INSERT INTO role_permissions (role_id,permission) VALUES (:roleId, :permission)";
    public static final String SELECT_ROLE_ID_BY_NAME_QUERY = "SELECT id FROM roles WHERE role_name = :roleName";
}
