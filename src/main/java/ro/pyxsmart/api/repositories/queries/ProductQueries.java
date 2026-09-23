package ro.pyxsmart.api.repositories.queries;

public class ProductQueries {



    public static final String INSERT_NEW_PRODUCT_QUERY = "INSERT INTO products (name, code, slug, short_description, description, category_id, brand_id, product_status, created_at  ) VALUES" +
            " (:name, :code, :slug, :shortDescription, :description, :categoryId, :brandId, :productStatus, NOW())";

    public static final String SELECT_ALL_PRODUCTS_QUERY = "SELECT * FROM products";

    public static final String SELECT_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id= :id";

    public static final String UPDATE_PRODUCT_BY_ID_QUERY = "UPDATE products SET name= :name, code= :code,slug= :slug, short_description= :shortDescription, description= :description, category_id= :categoryId, brand_id= :brandId, product_status= :productStatus, updated_at= NOW() WHERE id= :id";

    //
    //product Financial
    //

    public static final String INSERT_PRODUCT_FINANCIAL_QUERY = "INSERT INTO product_financials (product_id, purchase_price, selling_price, vat_rate_id ) VALUES ( :productId, :purchasePrice, :sellingPrice, :vatRateId )";

    public static final String SELECT_PRODUCT_FINANCIAL_BY_PRODUCT_ID_QUERY ="SELECT * FROM product_financials WHERE product_id = :productId ";



}
