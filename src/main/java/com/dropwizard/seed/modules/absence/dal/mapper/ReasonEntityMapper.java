package com.dropwizard.seed.modules.absence.dal.mapper;

import com.dropwizard.seed.modules.absence.dal.entity.ReasonEntity;
import com.dropwizard.seed.modules.absence.domain.Reason;

public class ReasonEntityMapper {

  public Reason map(ReasonEntity entity) {
    if (entity == null) {
      return null;
    }

    return Reason.builder(entity.getId(), entity.getName()).build();
  }

  public ReasonEntity map(Reason reason) {
    if (reason == null) {
      return null;
    }

    return ReasonEntity.builder(reason.getId(), reason.getName()).build();
  }
}
