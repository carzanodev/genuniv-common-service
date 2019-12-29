package carzanodev.genuniv.microservices.common.model.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseMeta {

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private Timestamp timestamp;

    public static ResponseMeta createBasicMeta(String msg) {
        return new ResponseMeta(msg, new Timestamp(System.currentTimeMillis()));
    }

}
