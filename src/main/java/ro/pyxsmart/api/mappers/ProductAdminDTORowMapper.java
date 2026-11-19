package ro.pyxsmart.api.mappers;

import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;
import ro.pyxsmart.api.models.modelDTO.ProductAdminDTO;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductAdminDTORowMapper implements RowMapper<ProductAdminDTO> {


    @Override
    public ProductAdminDTO mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        //TODO
        return ProductAdminDTO.builder() .build();
    }
}
