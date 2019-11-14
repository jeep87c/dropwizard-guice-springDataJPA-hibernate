package com.dropwizard.seed.modules.absence.dal.repository;

import com.dropwizard.seed.modules.absence.domain.Reason;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ReasonRepository {

  Collection<Reason> findAll();

  Optional<Reason> findById(UUID id);
}
