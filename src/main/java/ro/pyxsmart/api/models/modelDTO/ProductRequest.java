package ro.pyxsmart.api.models.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.pyxsmart.api.models.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {



    private String name;
    private String mpnCode;
    private String sku;
    private String bareCode;
    private String slug;

    private String shortDescription;
    private String description;

    private Long categoryId;
    private Long brandId;

    private ProductStatus status;
    private Boolean featured;


    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;

    private ProductFinancialRequest financial;
    private ProductInventoryRequest inventory;
    private List<ProductImagesRequest> images;


}
