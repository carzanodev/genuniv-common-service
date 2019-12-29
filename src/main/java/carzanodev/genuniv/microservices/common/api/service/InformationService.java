package carzanodev.genuniv.microservices.common.api.service;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;

import carzanodev.genuniv.microservices.common.model.dto.ResponseMeta;
import carzanodev.genuniv.microservices.common.model.dto.SourceInfo;
import carzanodev.genuniv.microservices.common.model.dto.StandardResponse;
import carzanodev.genuniv.microservices.common.persistence.repository.InformationRepository;

import static carzanodev.genuniv.microservices.common.util.MetaMessage.SIMPLE_MSG;

public abstract class InformationService {

    protected abstract InformationRepository getInfoRepo();

    public StandardResponse<SourceInfo> getInfo(String lastUpdated) {
        SourceInfo sourceInfo = new SourceInfo();

        if (!StringUtils.isEmpty(lastUpdated)) {
            Timestamp lastUpdatedTs = new Timestamp(TimeUnit.SECONDS.toMillis(Long.parseLong(lastUpdated)));
            int countUpdates = getInfoRepo().countAllByUpdatedAtGreaterThanEqual(lastUpdatedTs);
            sourceInfo.setUpdatesCount(countUpdates);
        }

        ResponseMeta meta = ResponseMeta.createBasicMeta(SIMPLE_MSG.make(""));

        return new StandardResponse<>(sourceInfo, meta);
    }

}
