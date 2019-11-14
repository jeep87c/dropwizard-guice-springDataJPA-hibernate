package com.dropwizard.seed.modules.absence.dal.repository;

import com.dropwizard.seed.modules.absence.dal.dao.ReasonEntityDao;
import com.dropwizard.seed.modules.absence.dal.mapper.ReasonEntityMapper;
import com.dropwizard.seed.modules.absence.domain.Reason;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class JpaReasonRepository implements ReasonRepository {

  private final ReasonEntityDao dao;
  private final ReasonEntityMapper mapper;

  @Inject
  public JpaReasonRepository(ReasonEntityDao dao, ReasonEntityMapper mapper) {
    this.dao = dao;
    this.mapper = mapper;
  }

  @Override
  public Collection<Reason> findAll() {
    return StreamSupport.stream(dao.findAll().spliterator(), true).map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public Optional<Reason> findById(UUID id) {
    return dao.findById(id).map(mapper::map);
  }
}
