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
                .mpnCode(productRequest.getMpnCode())
               .sku(productRequest.getSku())
               .bareCode(productRequest.getBareCode())
                .shortDescription(productRequest.getShortDescription())
                .description(productRequest.getDescription())
                .categoryId(productRequest.getCategoryId())
                .brandId(productRequest.getBrandId())
               .status(productRequest.getStatus())
               .featured(productRequest.getFeatured())
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

    public ProductAdminDTO toProductAdminDTO(Product productPar, ProductFinancial productFinancialPar){

         return   ProductAdminDTO.builder()
                 .product(productPar)
                 .productFinancial(productFinancialPar)
                 .build();

    }


}
