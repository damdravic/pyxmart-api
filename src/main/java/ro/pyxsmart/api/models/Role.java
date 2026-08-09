package ro.pyxsmart.api.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class Role {

    private Long id;
    private String name;
    private List<String> permissions;

}
