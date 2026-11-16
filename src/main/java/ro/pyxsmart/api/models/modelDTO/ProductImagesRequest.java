package ro.pyxsmart.api.models.modelDTO;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ProductImagesRequest {



    private String imageUrl;
    private String altText;
    private Integer sortOrder;
    private Boolean primary;

}
