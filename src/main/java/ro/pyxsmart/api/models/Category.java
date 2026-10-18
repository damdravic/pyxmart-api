package ro.pyxsmart.api.models;

import lombok.*;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private String longDescription;
    private String slug;
    private Integer sortOrder;
    private boolean isActive;


}
