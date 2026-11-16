package ro.pyxsmart.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImages {

    private Long id;
    private Long productId;
    private String imageUrl;
    private String altText;
    private Integer sortOrder;
    private Boolean primary;


}
