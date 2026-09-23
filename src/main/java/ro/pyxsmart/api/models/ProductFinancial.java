package ro.pyxsmart.api.models;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductFinancial {

    private Long id;
    private Long productId;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private  Long vatRateId;


}
