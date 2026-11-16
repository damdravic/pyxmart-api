package ro.pyxsmart.api.models.modelDTO;

import lombok.Data;

@Data
public class ProductInventoryRequest {

    private Long warehouseId;
    private Integer quantity;
    private Integer reservedQuantity;

}
