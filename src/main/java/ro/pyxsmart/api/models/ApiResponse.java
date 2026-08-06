package ro.pyxsmart.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApiResponse {

    private Instant  timestamp;
    private int status;
    private String message;
    private String path;
    private Map<?,?> data;

}
