package ro.pyxsmart.api.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class Category {

    private Long id;
    private String name;
    private String description;
    private String longDescription;
    private boolean isActive;
    private Long categoryParentId;

}
