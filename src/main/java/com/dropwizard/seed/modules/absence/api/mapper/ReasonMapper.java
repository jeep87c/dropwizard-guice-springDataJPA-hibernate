package com.dropwizard.seed.modules.absence.api.mapper;

import com.dropwizard.seed.modules.absence.api.dto.ReasonResponseV1;
import com.dropwizard.seed.modules.absence.domain.Reason;

public class ReasonMapper {

  public ReasonResponseV1 map(Reason reason) {
    if (reason == null) {
      return null;
    }

    return ReasonResponseV1.builder(reason.getId(), reason.getName()).build();
  }
}
