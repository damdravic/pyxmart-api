package ro.pyxsmart.api.models.modelDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
public class ProductFinancialRequest {

    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Long vatRateId;

}
