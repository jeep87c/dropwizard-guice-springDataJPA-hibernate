package com.dropwizard.seed.modules.absence.dal.mapper;

import com.dropwizard.seed.modules.absence.dal.entity.AbsenceEntity;
import com.dropwizard.seed.modules.absence.domain.Absence;
import com.dropwizard.seed.modules.absence.domain.Absence.AbsenceBuilder;

import javax.inject.Inject;

public class AbsenceEntityMapper {

  private final ReasonEntityMapper reasonEntityMapper;

  @Inject
  public AbsenceEntityMapper(ReasonEntityMapper reasonEntityMapper) {
    this.reasonEntityMapper = reasonEntityMapper;
  }

  public Absence map(AbsenceEntity entity) {
    if (entity == null) {
      return null;
    }

    AbsenceBuilder builder = Absence.builder(entity.getId(),
                                             entity.getStartsOn(),
                                             entity.getEndsOn(),
                                             reasonEntityMapper.map(entity.getReason()),
                                             entity.getHours(),
                                             entity.isUsingSickBank(),
                                             entity.isPaid());

    entity.getComment().ifPresent(builder::withComment);
    return builder.build();
  }

  public AbsenceEntity map(Absence absence) {
    if (absence == null) {
      return null;
    }

    AbsenceEntity.AbsenceEntityBuilder builder = AbsenceEntity.builder(absence.getId(),
                                                         absence.getStartsOn(),
                                                         absence.getEndsOn(),
                                                         reasonEntityMapper.map(absence.getReason()),
                                                         absence.getHours(),
                                                         absence.isUsingSickBank(),
                                                         absence.isPaid());

    absence.getComment().ifPresent(builder::withComment);

    return builder.build();
  }
}
