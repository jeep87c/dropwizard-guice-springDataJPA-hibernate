package com.dropwizard.seed.modules.absence.api.mapper;

import com.dropwizard.seed.modules.absence.api.dto.AddressCountryResponseV1;
import com.dropwizard.seed.modules.absence.api.dto.CountryResponseV1;
import com.dropwizard.seed.modules.absence.domain.Country;

import javax.inject.Inject;
import java.util.stream.Collectors;

public class CountryMapper {

  private final ProvinceMapper provinceMapper;

  @Inject
  public CountryMapper(ProvinceMapper provinceMapper) {
    this.provinceMapper = provinceMapper;
  }

  public CountryResponseV1 map(Country country) {
    if (country == null) {
      return null;
    }

    return CountryResponseV1.builder(country.getId(), country.getName())
                            .withProvinces(country.getProvinces()
                                                  .stream()
                                                  .map(provinceMapper::map)
                                                  .collect(Collectors.toSet()))
                            .build();
  }

  public AddressCountryResponseV1 mapToAddressCountryResponseV1(Country country){
    if (country == null) {
      return null;
    }

    return AddressCountryResponseV1.builder(country.getId(), country.getName()).build();
  }

}
