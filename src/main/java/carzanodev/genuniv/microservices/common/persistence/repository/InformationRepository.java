package carzanodev.genuniv.microservices.common.persistence.repository;

import java.sql.Timestamp;

public interface InformationRepository {

    int countAllByUpdatedAtGreaterThanEqual(Timestamp timestamp);

}
