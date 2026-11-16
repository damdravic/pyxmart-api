package ro.pyxsmart.api.models;

import lombok.*;

import java.sql.Date;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {

    private Long id;
    private String name;
    private String description;
    private String slug;
    private boolean active;
    private String logoUrl;
    private String logoUrlSecondary;
    private Date createdAt;
    private Date updatedAt;


}
