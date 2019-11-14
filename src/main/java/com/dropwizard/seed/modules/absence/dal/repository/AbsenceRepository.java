package com.dropwizard.seed.modules.absence.dal.repository;

import com.dropwizard.seed.modules.absence.domain.Absence;

import java.time.Instant;
import java.util.Collection;

public interface AbsenceRepository {

  Absence save(Absence absence);

  Collection<Absence> findAll();

  boolean existingAbsenceBetweenDates(Instant startsOn, Instant endsOn);
}
