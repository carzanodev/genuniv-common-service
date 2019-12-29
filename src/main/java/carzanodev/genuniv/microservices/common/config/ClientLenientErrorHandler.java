package carzanodev.genuniv.microservices.common.config;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

public class ClientLenientErrorHandler extends DefaultResponseErrorHandler {

    @Override
    protected boolean hasError(HttpStatus statusCode) {
        return statusCode.series() == null || statusCode.series() == Series.SERVER_ERROR;
    }

    @Override
    protected boolean hasError(int unknownStatusCode) {
        Series series = Series.resolve(unknownStatusCode);
        return series == Series.SERVER_ERROR;
    }

    @Override
    protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        byte[] body = getResponseBody(response);
        Charset charset = getCharset(response);

        switch (statusCode.series()) {
            case CLIENT_ERROR:
                return;
            case SERVER_ERROR:
                throw HttpServerErrorException.create(statusCode, statusText, headers, body, charset);
            default:
                throw new UnknownHttpStatusCodeException(statusCode.value(), statusText, headers, body, charset);
        }
    }

}
