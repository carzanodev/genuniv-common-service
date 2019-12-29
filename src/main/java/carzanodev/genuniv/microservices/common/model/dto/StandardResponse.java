package carzanodev.genuniv.microservices.common.model.dto;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(Include.NON_NULL)
public class StandardResponse<T> {

    private static DateTimeFormatter commonFormat = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @JsonProperty("response")
    private T response;

    @JsonProperty("meta")
    private ResponseMeta meta;

    @JsonProperty("error")
    private ApiError error;

    public StandardResponse(T response, ResponseMeta meta) {
        this.response = response;
        this.meta = meta;
    }

    public StandardResponse(ApiError error) {
        this.error = error;
    }

    public boolean isError() {
        return error != null || response == null;
    }

}

