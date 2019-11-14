package com.dropwizard.seed.modules.absence.dal.repository;

import com.dropwizard.seed.modules.absence.dal.dao.CountryEntityDao;
import com.dropwizard.seed.modules.absence.dal.mapper.CountryEntityMapper;
import com.dropwizard.seed.modules.absence.domain.Country;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class JpaCountryRepository implements CountryRepository {

  private final CountryEntityDao dao;
  private final CountryEntityMapper mapper;

  @Inject
  public JpaCountryRepository(CountryEntityDao dao, CountryEntityMapper mapper) {
    this.dao = dao;
    this.mapper = mapper;
  }

  @Override
  public Collection<Country> findAll() {
    return StreamSupport.stream(dao.findAll().spliterator(), true).map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public Optional<Country> findById(UUID id) {
    return dao.findById(id).map(mapper::map);
  }
}
