package ro.pyxsmart.api.models.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.pyxsmart.api.models.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdminDTO {

    private Product product;
    private ProductFinancial productFinancial;
    private ProductInventory productInventory;
    private List<ProductImages> productImages;


}
