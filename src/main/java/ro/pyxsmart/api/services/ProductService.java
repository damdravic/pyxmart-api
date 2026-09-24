package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;

public interface ProductService {

     ProductAdminDTO createNewProduct(ProductRequest productRequest);

     ProductAdminDTO updateProductById (Long id, ProductRequest requestProduct);


}
