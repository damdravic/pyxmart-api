package ro.pyxsmart.api.services;

import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;

import java.util.List;

public interface ProductService {

     ProductAdminDTO createNewProduct(ProductRequest productRequest);

     ProductAdminDTO updateProductById (Long id, ProductRequest requestProduct);


      List<ProductAdminDTO> getAll();
}
