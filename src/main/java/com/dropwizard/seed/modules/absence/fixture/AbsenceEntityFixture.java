package com.dropwizard.seed.modules.absence.fixture;

import com.dropwizard.seed.modules.absence.dal.dao.AbsenceEntityDao;
import com.dropwizard.seed.modules.absence.dal.entity.AbsenceEntity;
import java.util.List;
import javax.inject.Inject;

public class AbsenceEntityFixture {

  private final AbsenceEntityDao dao;

  @Inject
  public AbsenceEntityFixture(AbsenceEntityDao dao) {
    this.dao = dao;
  }

  public void deleteAll() {
    dao.deleteAll();
  }

  public AbsenceEntity givenAbsenceEntity(AbsenceEntity.AbsenceEntityBuilder absenceEntityBuilder) {
    return dao.save(absenceEntityBuilder.build());
  }

  public List<AbsenceEntity> findAll() {
    return dao.findAll();
  }
}
