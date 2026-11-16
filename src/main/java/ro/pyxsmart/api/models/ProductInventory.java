package ro.pyxsmart.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInventory {

    private Long id;
    private Long productId;
    private Long warehouseId;
    private Integer quantity;
    private Integer reservedQuantity;


}
