package com.dropwizard.seed.modules.absence.bootstrapper.entity;

import com.dropwizard.seed.core.bootstrapper.EntityBootstrapper;
import com.dropwizard.seed.modules.absence.dal.dao.AbsenceEntityDao;
import com.dropwizard.seed.modules.absence.dal.dao.ReasonEntityDao;
import com.dropwizard.seed.modules.absence.dal.entity.AbsenceEntity;
import com.dropwizard.seed.modules.absence.dal.entity.ReasonEntity;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Map;

public class AbsenceEntityBootstrapper implements EntityBootstrapper {

  private final AbsenceEntityDao absenceEntityDao;
  private final ReasonEntityDao reasonEntityDao;

  @Inject
  public AbsenceEntityBootstrapper(AbsenceEntityDao absenceEntityDao,
                                   ReasonEntityDao reasonEntityDao) {
    this.absenceEntityDao = absenceEntityDao;
    this.reasonEntityDao = reasonEntityDao;
  }

  public void seed() {
    ReasonEntity reasonEntity = reasonEntityDao.save(ReasonEntity.builder(TEMPORARY_ID, "Cooking papaya tart").build());
    ReasonEntity reasonEntity2 = reasonEntityDao.save(ReasonEntity.builder(TEMPORARY_ID, "Cooking lobster risotto")
                                                                  .build());

    absenceEntityDao.save(AbsenceEntity.builder(TEMPORARY_ID,
                                                Instant.parse("1998-02-01T12:27:49.072881Z"),
                                                Instant.parse("1998-02-02T12:27:49.072881Z"),
                                                reasonEntity,
                                                Map.of(Instant.parse("1998-02-01T12:27:49.072881Z"),
                                                       10f,
                                                       Instant.parse("1998-02-02T12:27:49.072881Z"),
                                                       8f),
                                                true,
                                                true).build());

    absenceEntityDao.save(AbsenceEntity.builder(TEMPORARY_ID,
                                                Instant.parse("1997-03-01T12:27:49.072881Z"),
                                                Instant.parse("1997-03-02T12:27:49.072881Z"),
                                                reasonEntity2,
                                                Map.of(Instant.parse("1997-03-01T12:27:49.072881Z"),
                                                       9f,
                                                       Instant.parse("1997-03-02T12:27:49.072881Z"),
                                                       7f),
                                                false,
                                                false).build());
  }
}
