package carzanodev.genuniv.microservices.common.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import carzanodev.genuniv.microservices.common.model.dto.ApiError;
import carzanodev.genuniv.microservices.common.model.dto.StandardResponse;
import carzanodev.genuniv.microservices.common.util.time.TimestampUtility;

public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidTargetEntityException.class, InvalidReferenceValueException.class, NonEmptyException.class})
    public ResponseEntity<StandardResponse<Object>> handleBadRequests(Exception e, WebRequest request) {
        ApiError apiError = new ApiError(
                TimestampUtility.now(),
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage());

        StandardResponse<Object> errorResponse = new StandardResponse<>(apiError);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static class InvalidTargetEntityException extends Exception {
        public InvalidTargetEntityException(String entity, String idNotFound) {
            super("Target `" + idNotFound + "` is inexistent in `" + entity + "`");
        }
    }

    public static class InvalidReferenceValueException extends Exception {
        public InvalidReferenceValueException(String fieldName, String invalidValue) {
            super("Field `" + fieldName + "` with value `" + invalidValue + "` cannot be resolved! Appropriate value expected!");
        }
    }

    public static class NonEmptyException extends Exception {
        public NonEmptyException(String fieldName) {
            super("Field `" + fieldName + "` is expected to contain a non-null or non-zero value!");
        }
    }

}
