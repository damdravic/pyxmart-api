package ro.pyxsmart.api.models.modelDTO;

import lombok.Builder;
import lombok.Data;
import ro.pyxsmart.api.models.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductAdminDTO {

    private Long id;
    private String name;
    private String code;
    private String slug;
    private String shortDescription;
    private String description;
    private Long categoryId;
    private Long brandId;

    private ProductStatus status;
    private boolean featured;

    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

   // private Long productId;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Long vatRateId;


}
