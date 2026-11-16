package ro.pyxsmart.api.mappers;

import org.springframework.stereotype.Component;
import ro.pyxsmart.api.models.Product;
import ro.pyxsmart.api.models.ProductFinancial;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;
import ro.pyxsmart.api.models.modelDTO.ProductFinancialRequest;
import ro.pyxsmart.api.models.modelDTO.ProductRequest;

@Component
public class ProductMapper {

    public Product productFromProductRequest(ProductRequest productRequest){

       return Product.builder()
                .name(productRequest.getName())
                .code(productRequest.getCode())
                .shortDescription(productRequest.getShortDescription())
                .description(productRequest.getDescription())
                .categoryId(productRequest.getCategoryId())
                .brandId(productRequest.getBrandId())
                .build();

    }

    public ProductFinancial productFinancialFromProductFinancialRequest(ProductFinancialRequest productFinancialRequest, Long productId){

        return ProductFinancial.builder()
                .productId(productId)
                .purchasePrice(productFinancialRequest.getPurchasePrice())
                .sellingPrice(productFinancialRequest.getSellingPrice())
                .vatRateId(productFinancialRequest.getVatRateId())
                .build();
    }

    public ProductAdminDTO toProductAdminDTO(Product product, ProductFinancial productFinancial){

         return   ProductAdminDTO.builder()
                 .id(product.getId())
                 .name(product.getName())
                 .code(product.getCode())
                 .slug(product.getSlug())
                 .shortDescription(product.getShortDescription())
                 .description(product.getDescription())
                 .categoryId(product.getCategoryId())
                 .brandId(product.getBrandId())
                 .purchasePrice(productFinancial.getPurchasePrice())
                 .sellingPrice(productFinancial.getSellingPrice())
                 .vatRateId(productFinancial.getVatRateId())
                 .build();

    }


}
