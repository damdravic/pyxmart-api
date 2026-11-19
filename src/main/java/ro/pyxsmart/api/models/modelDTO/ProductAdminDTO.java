package ro.pyxsmart.api.models.modelDTO;

import lombok.*;
import ro.pyxsmart.api.models.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductAdminDTO {

    private Product product;
    private ProductFinancial productFinancial;
    private ProductInventory productInventory;
    private List<ProductImages> productImages;


}
