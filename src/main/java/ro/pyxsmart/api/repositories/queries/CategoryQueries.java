package ro.pyxsmart.api.repositories.queries;

public class CategoryQueries {

    public static final String CREATE_CATEGORY_QUERY = " INSERT INTO categories (parent_id, name, description, long_description, slug, sort_order) VALUES " +
            " (:parentId, :name, :description, :longDescription, :slug, :sortOrder)" ;

    public static final String SELECT_ALL_CATEGORIES_QUERY = " SELECT * FROM categories ";

    public static final String SELECT_CATEGORY_BY_ID_QUERY = "SELECT * FROM categories WHERE id = :id ";

    public static final String SELECT_CATEGORY_BY_NAME_QUERY = " SELECT * FROM categories WHERE name =:name";

    public static final String EXISTS_CATEGORY_BY_NAME_QUERY = "SELECT EXISTS (SELECT 1 FROM categories WHERE name = :name)";
    public static final String   SELECT_CATEGORY_BY_PARENT_AND_NAME_QUERY =" SELECT * FROM categories WHERE  name = :name AND " +
          "(parent_id = :parentId OR (parent_id IS NULL AND :parentId IS NULL)))" ;

}
