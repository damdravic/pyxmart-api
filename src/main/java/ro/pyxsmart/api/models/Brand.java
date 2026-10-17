package ro.pyxsmart.api.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class Brand {

    private Long id;
    private String name;
    private String description;


}
