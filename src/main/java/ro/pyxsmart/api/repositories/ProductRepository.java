package ro.pyxsmart.api.repositories;

import ro.pyxsmart.api.models.Product;
import ro.pyxsmart.api.models.ProductImages;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;

import java.util.List;

public interface ProductRepository {

    // Product

    Product createProduct(ProductRequest productRequest);

    List<ProductAdminDTO> getAllProducts();

    Product getProductById(Long id);

    Product updateProductById(Long id, Product product);

    void deleteProductById(Long id);

    void verifyCodeAndSlug(String code, String slug);

    ProductImages createProductImage(ProductImages productImage);

    void deleteProductImagesByProductId(Long id);
}
