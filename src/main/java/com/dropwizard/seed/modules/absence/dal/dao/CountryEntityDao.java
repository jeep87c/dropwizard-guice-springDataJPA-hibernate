package com.dropwizard.seed.modules.absence.dal.dao;

import com.dropwizard.seed.modules.absence.dal.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CountryEntityDao extends CrudRepository<CountryEntity, UUID> {

  Optional<CountryEntity> findByName(String name);
}
