package com.dropwizard.seed.modules.absence.dal.mapper;

import com.dropwizard.seed.modules.absence.dal.entity.CountryEntity;
import com.dropwizard.seed.modules.absence.domain.Country;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class CountryEntityMapper {

  private final ProvinceEntityMapper provinceEntityMapper;

  @Inject
  public CountryEntityMapper(ProvinceEntityMapper provinceEntityMapper) {
    this.provinceEntityMapper = provinceEntityMapper;
  }

  public Country map(CountryEntity entity) {
    if (entity == null) {
      return null;
    }

    return Country.builder(entity.getId(), entity.getName()).setProvinces(entity.getProvinces()
                                                                                .stream()
                                                                                .map(provinceEntityMapper::map)
                                                                                .collect(Collectors.toSet())).build();
  }

  public CountryEntity map(Country country) {
    if (country == null) {
      return null;
    }

    return CountryEntity.builder(country.getId(), country.getName())
                        .withProvinces(country.getProvinces()
                                              .stream()
                                              .map(provinceEntityMapper::map)
                                              .collect(Collectors.toSet()))
                        .build();
  }
}
