package ro.pyxsmart.api.repositories;

import ro.pyxsmart.api.models.ProductInventory;
import ro.pyxsmart.api.models.modelDTO.ProductInventoryRequest;

public interface ProductInventoryRepository {

    ProductInventory createProductInventory (Long productId, ProductInventoryRequest productInventoryRequest);

    ProductInventory getProductInventoryByProductId(Long productId);






}
