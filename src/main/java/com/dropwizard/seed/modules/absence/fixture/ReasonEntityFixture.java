package com.dropwizard.seed.modules.absence.fixture;

import com.dropwizard.seed.modules.absence.dal.dao.ReasonEntityDao;
import com.dropwizard.seed.modules.absence.dal.entity.ReasonEntity;
import com.dropwizard.seed.modules.absence.dal.entity.ReasonEntity.ReasonEntityBuilder;

import javax.inject.Inject;

public class ReasonEntityFixture {

  private final ReasonEntityDao dao;

  @Inject
  public ReasonEntityFixture(ReasonEntityDao dao) {
    this.dao = dao;
  }

  public void deleteAll() {
    dao.deleteAll();
  }

  public ReasonEntity givenReasonEntity(ReasonEntityBuilder reasonEntityBuilder) {
    return this.dao.save(reasonEntityBuilder.build());
  }
}
