package ro.pyxsmart.api.models.modelDTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRequest {



    private String name;
    private String code;
    private String slug;
    private String shortDescription;
    private String description;
    private Long categoryId;
    private Long brandId;
    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;

    private Long productId;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Long vatRateId;


}
