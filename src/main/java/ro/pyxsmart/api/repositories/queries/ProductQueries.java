package ro.pyxsmart.api.repositories.queries;

public class ProductQueries {


    public static final String INSERT_NEW_PRODUCT_QUERY = "INSERT INTO products (name, mpn_code, sku, bare_code, slug, short_description, description, category_id, brand_id, product_status, created_at ) VALUES" +
            " (:name, :mpnCode, :sku, :bareCode, :slug, :shortDescription, :description, :categoryId, :brandId, :productStatus, NOW())";

    public static final String SELECT_ALL_PRODUCTS_ADMIN_QUERY = "SELECT * FROM products p LEFT JOIN product_financials pf ON pf.product_id = p.id ";

    public static final String SELECT_PRODUCT_BY_ID_QUERY = "SELECT * FROM products WHERE id= :id";

    public static final String UPDATE_PRODUCT_BY_ID_QUERY = "UPDATE products SET name= :name, code= :code,slug= :slug, short_description= :shortDescription, description= :description, category_id= :categoryId, brand_id= :brandId, product_status= :productStatus, updated_at= NOW() WHERE id= :id";

    public static final String SELECT_COUNT_PRODUCT_BY_CODE_QUERY = "SELECT COUNT(*) FROM products WHERE mpn_code = :mpnCode ";
    public static final String SELECT_COUNT_PRODUCT_BY_SLUG_QUERY = "SELECT COUNT(*) FROM products WHERE slug = :slug";

    //
    //product Financial
    //

    public static final String INSERT_PRODUCT_FINANCIAL_QUERY = "INSERT INTO product_financials (product_id, purchase_price, selling_price, vat_rate_id ) VALUES ( :productId, :purchasePrice, :sellingPrice, :vatRateId )";

    public static final String SELECT_PRODUCT_FINANCIAL_BY_PRODUCT_ID_QUERY = "SELECT * FROM product_financials WHERE product_id = :productId ";

    public static final String SELECT_PRODUCT_FINANCIAL_BY_VAT_ID_QUERY = "SELECT COUNT(*) FROM vat_rates WHERE id = :vatRateId";

    //
    // Product Inventory Queries
    //

    public static final String INSERT_PRODUCT_INVENTORY_QUERY = " INSERT INTO product_inventory (product_id, warehouse_id, quantity, reserved_quantity ) " +
            "VALUES (:productId, :warehouseId, :quantity, :reservedQuantity)";

    public static final String SELECT_COUNT_WH_PRODUCT_EXISTS_QUERY = "SELECT COUNT(*) FROM product_inventory WHERE warehouse_id = :warehouseId AND product_id = :productId";

    //
    // Product Inventory -Warehouse Queries
    //

    public static final String SELECT_COUNT_WAREHOUSE_BY_ID = "SELECT COUNT(*) FROM warehouses WHERE id = :id ";


    //
    //Product images
    //

    public static final String INSERT_NEW_PRODUCT_IMAGE_QUERY = "INSERT INTO product_images (product_id, image_url, alt_text, sort_order, is_primary, created_at) " +
            "VALUES (:productId, :imageUrl, :altText, :sortOrder, :isPrimary, NOW()";

    public static final String DELETE_PRODUCT_IMAGE_BY_PRODUCT_ID_QUERY= "DELETE FROM product_images WHERE product_id = :productId";

}