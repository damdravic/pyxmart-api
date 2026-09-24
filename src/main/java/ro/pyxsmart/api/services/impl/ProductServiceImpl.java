package ro.pyxsmart.api.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.pyxsmart.api.mappers.ProductMapper;
import ro.pyxsmart.api.models.Product;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;
import ro.pyxsmart.api.repositories.ProductFinancialRepository;
import ro.pyxsmart.api.repositories.ProductRepository;
import ro.pyxsmart.api.services.ProductService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductFinancialRepository productFinancialRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductAdminDTO createNewProduct(ProductRequest productRequest) {

        Product product = productRepository.createProduct(productMapper.productFromProductRequest(productRequest));
        ProductFinancial productFinancial = productMapper.productFinancialFromProductRequest(productRequest,product.getId());
        productFinancialRepository.createProdFinancial(productFinancial);

        return productMapper.toProductAdminDTO(product,productFinancial);

    }

    @Override
    public ProductAdminDTO updateProductById(Long id, ProductRequest requestProduct) {
        //TODO - implement product update
        return null;
    }
}
