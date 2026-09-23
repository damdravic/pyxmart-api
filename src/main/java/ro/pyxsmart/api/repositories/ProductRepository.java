package ro.pyxsmart.api.repositories;

import ro.pyxsmart.api.models.Product;

import java.util.List;

public interface ProductRepository {

    // Product

    public Product createProduct(Product product);

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public Product updateProductById(Long id , Product product);

    public void deleteProductById(Long id);


}
