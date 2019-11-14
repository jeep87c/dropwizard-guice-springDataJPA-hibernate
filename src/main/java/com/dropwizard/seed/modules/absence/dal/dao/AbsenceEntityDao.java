package com.dropwizard.seed.modules.absence.dal.dao;

import com.dropwizard.seed.core.dal.repository.EntityDao;
import com.dropwizard.seed.modules.absence.dal.entity.AbsenceEntity;
import java.time.Instant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AbsenceEntityDao extends EntityDao<AbsenceEntity> {

  @Query(nativeQuery = true,
         value = "SELECT count(*) from AbsenceEntity WHERE ((AbsenceEntity.startsOn >= ?1 AND AbsenceEntity.startsOn <= ?2 ) OR (AbsenceEntity.endsOn >= ?1 AND AbsenceEntity.endsOn <= ?2))")
  int existingAbsenceBetweenDates(@Param("startDate") Instant startDate, @Param("endDate") Instant endDate);
}
