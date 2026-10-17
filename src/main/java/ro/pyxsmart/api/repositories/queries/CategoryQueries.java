package ro.pyxsmart.api.repositories.queries;

public class CategoryQueries {

    public static final String CREATE_CATEGORY_QUERY = " INSERT INTO categories (name, description, category_parent_id) VALUES " +
            " (:name, :description, :categoryParentId)";

}
