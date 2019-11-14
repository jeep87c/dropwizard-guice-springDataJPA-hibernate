package com.dropwizard.seed.modules.absence.api.mapper;

import com.dropwizard.seed.modules.absence.api.dto.AbsenceCreateRequestV1;
import com.dropwizard.seed.modules.absence.api.dto.AbsenceResponseV1;
import com.dropwizard.seed.modules.absence.dal.repository.ReasonRepository;
import com.dropwizard.seed.modules.absence.domain.Absence;
import com.dropwizard.seed.modules.absence.domain.Absence.AbsenceBuilder;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.UUID;

public class AbsenceMapper {

  private static final UUID TEMPORARY_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

  private final ReasonMapper reasonMapper;
  private final ReasonRepository reasonRepository;

  @Inject
  public AbsenceMapper(ReasonMapper reasonMapper,
                       ReasonRepository reasonRepository) {
    this.reasonMapper = reasonMapper;
    this.reasonRepository = reasonRepository;
  }

  public Absence map(AbsenceCreateRequestV1 absenceCreateRequestV1) {
    if (absenceCreateRequestV1 == null) {
      return null;
    }

    AbsenceBuilder builder = Absence.builder(TEMPORARY_ID,
                                             absenceCreateRequestV1.getStartsOn(),
                                             absenceCreateRequestV1.getEndsOn(),
                                             reasonRepository.findById(absenceCreateRequestV1.getReasonId())
                                                             .orElseThrow(() -> new NotFoundException(String.format(
                                                                        "Can't find reason with %s",
                                                                        absenceCreateRequestV1.getReasonId()
                                                                                              .toString()))),
                                             absenceCreateRequestV1.getHours(),
                                             absenceCreateRequestV1.isUsingSickBank(),
                                             absenceCreateRequestV1.isPaid());
    absenceCreateRequestV1.getComment().ifPresent(builder::withComment);

    return builder.build();
  }

  public AbsenceResponseV1 map(Absence absence) {
    if (absence == null) {
      return null;
    }

    AbsenceResponseV1.AbsenceResponseV1Builder builder = AbsenceResponseV1.builder(absence.getId(),
                                                                 absence.getStartsOn(),
                                                                 absence.getEndsOn(),
                                                                 reasonMapper.map(absence.getReason()),
                                                                 absence.getHours(),
                                                                 absence.isUsingSickBank(),
                                                                 absence.isPaid());
    absence.getComment().ifPresent(builder::withComment);

    return builder.build();
  }
}
