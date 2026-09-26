package ro.pyxsmart.api.models.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.pyxsmart.api.models.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdminDTO {

    private Long id;
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Long vatRateId;


}
