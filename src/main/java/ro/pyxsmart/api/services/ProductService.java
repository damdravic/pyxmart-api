package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.models.modelDTO.ProductDTO;

public interface ProductService {

     ProductDTO createNewProduct(ProductRequest productRequest);

     ProductDTO updateProductById (Long id, ProductRequest requestProduct);


}
