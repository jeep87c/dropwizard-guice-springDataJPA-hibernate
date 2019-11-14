package com.dropwizard.seed.modules.absence.dal.mapper;

import com.dropwizard.seed.modules.absence.dal.entity.AddressEntity;
import com.dropwizard.seed.modules.absence.dal.entity.AddressEntity.AddressEntityBuilder;
import com.dropwizard.seed.modules.absence.domain.Address;
import com.dropwizard.seed.modules.absence.domain.Address.AddressBuilder;

import javax.inject.Inject;

public class AddressEntityMapper {

  private final CountryEntityMapper countryEntityMapper;
  private final ProvinceEntityMapper provinceEntityMapper;

  @Inject
  public AddressEntityMapper(CountryEntityMapper countryEntityMapper, ProvinceEntityMapper provinceEntityMapper) {
    this.countryEntityMapper = countryEntityMapper;
    this.provinceEntityMapper = provinceEntityMapper;
  }

  public Address map(AddressEntity entity) {
    if (entity == null) {
      return null;
    }

    AddressBuilder builder = Address.builder();
    entity.getLine1().ifPresent(builder::withLine1);
    entity.getLine2().ifPresent(builder::withLine2);
    entity.getLine3().ifPresent(builder::withLine3);
    entity.getPostalCode().ifPresent(builder::withPostalCode);
    entity.getCity().ifPresent(builder::withCity);
    entity.getProvince().ifPresent(province -> builder.withProvince(provinceEntityMapper.map(province)));
    entity.getCountry().ifPresent(country -> builder.withCountry(countryEntityMapper.map(country)));

    return builder.build();
  }

  public AddressEntity map(Address address) {
    if (address == null) {
      return null;
    }

    AddressEntityBuilder builder = AddressEntity.builder();
    address.getLine1().ifPresent(builder::withLine1);
    address.getLine2().ifPresent(builder::withLine2);
    address.getLine3().ifPresent(builder::withLine3);
    address.getPostalCode().ifPresent(builder::withPostalCode);
    address.getCity().ifPresent(builder::withCity);
    address.getProvince().ifPresent(province -> builder.withProvince(provinceEntityMapper.map(province)));
    address.getCountry().ifPresent(country -> builder.withCountry(countryEntityMapper.map(country)));

    return builder.build();
  }
}
