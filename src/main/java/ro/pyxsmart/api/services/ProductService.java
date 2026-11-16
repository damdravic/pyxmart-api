package ro.pyxsmart.api.services;

import org.springframework.web.multipart.MultipartFile;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;

import java.io.IOException;
import java.util.List;

public interface ProductService {

     ProductAdminDTO createNewProduct(ProductRequest productRequest, List<MultipartFile> multipartFiles) throws IOException;

     ProductAdminDTO updateProductById (Long id, ProductRequest requestProduct);

     ProductFinancial createProductFinancial(ProductFinancial financial);

      List<ProductAdminDTO> getAll();

      List<String> sevaProductImages(List<MultipartFile> images);
}
