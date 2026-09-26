package ro.pyxsmart.api.models.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {



    private String name;
    private String code;
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
