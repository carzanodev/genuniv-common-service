package carzanodev.genuniv.microservices.common.cache;

import java.util.Collection;
import java.util.Collections;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import carzanodev.genuniv.microservices.common.model.dto.SourceInfo;
import carzanodev.genuniv.microservices.common.model.dto.StandardResponse;
import carzanodev.genuniv.microservices.common.util.cache.DataCache;

@Slf4j
@RequiredArgsConstructor
public abstract class ApiCache<ID, DATA, RESPONSE> extends DataCache<ID, DATA> {

    protected final RestTemplate restTemplate;
    protected final String apiUrl;
    protected final String apiInfoUrl;

    protected SourceInfo info;

    @Override
    protected boolean mustLoad() {
        try {
            StandardResponse<SourceInfo> apiInfo = getApiInfo();

            if (apiInfo.isError()) {
                log.warn("Service returned an error: " + apiInfo.getError());
                return false;
            }

            info = apiInfo.getResponse();
            return mustLoad0();
        } catch (Exception e) {
            log.warn("Service is currently unavailable: " + e);
            return false;
        }
    }

    @Override
    protected LoadResult retrieve() {
        try {
            StandardResponse<RESPONSE> apiData = getApiData();

            if (apiData.isError()) {
                log.warn("Service returned an error: " + apiData.getError());
                return new LoadResult(Collections.EMPTY_SET, false);
            }

            return new LoadResult(getResultData(apiData), true);
        } catch (Exception e) {
            log.warn("Service is currently unavailable: " + e);
            return new LoadResult(Collections.EMPTY_SET, false);
        }
    }

    protected boolean mustLoad0() {
        return info.getUpdatesCount() > 0;
    }

    protected StandardResponse<SourceInfo> getApiInfo() {
        return restTemplate.exchange(apiInfoUrl + "?last_updated=" + getLastLoaded(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StandardResponse<SourceInfo>>() {
                }).getBody();
    }

    protected StandardResponse<RESPONSE> getApiData() {
        return restTemplate.exchange(apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StandardResponse<RESPONSE>>() {
                }).getBody();
    }

    protected abstract Collection<DATA> getResultData(StandardResponse<RESPONSE> response);

}
