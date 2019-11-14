package com.dropwizard.seed.modules.absence.dal.repository;

import com.dropwizard.seed.modules.absence.domain.Country;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface CountryRepository {

  Collection<Country> findAll();

  Optional<Country> findById(UUID id);
}
