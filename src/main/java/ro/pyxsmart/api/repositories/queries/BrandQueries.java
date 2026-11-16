package ro.pyxsmart.api.repositories.queries;

public class BrandQueries {

    public static final String INSERT_NEW_BRAND_QUERY = " INSERT INTO brands" +
            " (name, description, slug, active, logo_url, logo_url_secondary, created_at) " +
            "VALUES ( :name, :description, :slug, :active , :logoUrl, :logoUrlSecondary, NOW()" +
            " )";

    public static final String SELECT_ALL_BRANDS_QUERY = "SELECT * FROM brands";

    public static final String UPDATE_BRAND_QUERY = "UPDATE brands SET name = :name, description = :description,slug = :slug, active = :active, " +
            "logo_url = :logoUrl, logo_url_secondary = :logoUrlSecondary, updated_at = NOW() WHERE id = :id";

}
