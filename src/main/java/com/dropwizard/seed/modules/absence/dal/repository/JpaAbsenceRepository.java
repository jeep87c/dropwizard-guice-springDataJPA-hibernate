package com.dropwizard.seed.modules.absence.dal.repository;

import com.dropwizard.seed.modules.absence.dal.dao.AbsenceEntityDao;
import com.dropwizard.seed.modules.absence.dal.mapper.AbsenceEntityMapper;
import com.dropwizard.seed.modules.absence.domain.Absence;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

public class JpaAbsenceRepository implements AbsenceRepository {

  private final AbsenceEntityDao dao;
  private final AbsenceEntityMapper mapper;

  @Inject
  public JpaAbsenceRepository(AbsenceEntityDao dao, AbsenceEntityMapper mapper) {
    this.dao = dao;
    this.mapper = mapper;
  }

  @Override
  public Absence save(Absence absence) {
    return mapper.map(this.dao.save(mapper.map(absence)));
  }

  @Override
  public Collection<Absence> findAll() {
    return dao.findAll()
              .parallelStream()
              .map(mapper::map)
              .collect(Collectors.toList());
  }

  @Override
  public boolean existingAbsenceBetweenDates(Instant startsOn, Instant endsOn){
    return dao.existingAbsenceBetweenDates(startsOn, endsOn) >= 1;
  }
}
