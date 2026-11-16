package ro.pyxsmart.api.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.pyxsmart.api.exceptions.AlreadyExistsException;
import ro.pyxsmart.api.models.ProductInventory;
import ro.pyxsmart.api.models.modelDTO.ProductInventoryRequest;
import ro.pyxsmart.api.repositories.ProductInventoryRepository;

import java.util.Map;
import java.util.Objects;

import static ro.pyxsmart.api.repositories.queries.ProductQueries.*;

@Repository
@RequiredArgsConstructor
public class ProductInventoryRepositoryImpl  implements ProductInventoryRepository {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public ProductInventory createProductInventory(Long productId, ProductInventoryRequest productInventoryRequest) {


        if(verifyWarehouse(productInventoryRequest.getWarehouseId())) {
          checkIfProductExistsInWarehouse(productInventoryRequest.getWarehouseId(),productId);

            KeyHolder kh = new GeneratedKeyHolder();
            MapSqlParameterSource piParams = getPiParams(productId, productInventoryRequest);

            jdbc.update(INSERT_PRODUCT_INVENTORY_QUERY, piParams);
             return ProductInventory.builder()
                     .id(Objects.requireNonNull(kh.getKey()).longValue())
                     .warehouseId(productInventoryRequest.getWarehouseId())
                     .quantity(productInventoryRequest.getQuantity())
                     .reservedQuantity(productInventoryRequest.getReservedQuantity())
                     .build();

         }
        return null;
    }

    private void checkIfProductExistsInWarehouse(Long warehouseId, Long productId) {
         Integer count = jdbc.queryForObject(SELECT_COUNT_WH_PRODUCT_EXISTS_QUERY,Map.of("warehouseId",warehouseId,"productId",productId),Integer.class);

         if(count != null && count > 0){
             throw new AlreadyExistsException("This Product exist in this Warehouse !!!");
         }

    }

    private boolean verifyWarehouse(Long warehouseId) {

        Integer count = jdbc.queryForObject(SELECT_COUNT_WAREHOUSE_BY_ID, Map.of("id",warehouseId),Integer.class);
                return count != null && count > 0;

    }

    private MapSqlParameterSource getPiParams(Long productId, ProductInventoryRequest productInventoryRequest) {

        return new MapSqlParameterSource().addValue("productId",productId)
                .addValue("warehouseId",productInventoryRequest.getWarehouseId())
                .addValue("quantity", productInventoryRequest.getQuantity())
                .addValue("reservedQuantity",productInventoryRequest.getReservedQuantity());

    }



    @Override
    public ProductInventory getProductInventoryByProductId(Long productId) {
        return null;
    }
}
