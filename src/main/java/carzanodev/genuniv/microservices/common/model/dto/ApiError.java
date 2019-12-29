package carzanodev.genuniv.microservices.common.model.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiError {

    @JsonProperty("timestamp")
    private Timestamp timestamp;

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

}
