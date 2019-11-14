package com.dropwizard.seed.modules.absence.api.mapper;

import com.dropwizard.seed.modules.absence.api.dto.AddressResponseV1;
import com.dropwizard.seed.modules.absence.api.dto.AddressResponseV1.AddressResponseV1Builder;
import com.dropwizard.seed.modules.absence.api.dto.AddressSaveRequestV1;
import com.dropwizard.seed.modules.absence.dal.repository.CountryRepository;
import com.dropwizard.seed.modules.absence.domain.Address;
import com.dropwizard.seed.modules.absence.domain.Address.AddressBuilder;
import com.dropwizard.seed.modules.absence.domain.Country;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class AddressMapper {

  private final ProvinceMapper provinceMapper;
  private final CountryMapper countryMapper;
  private final CountryRepository countryRepository;

  @Inject
  public AddressMapper(ProvinceMapper provinceMapper, CountryMapper countryMapper, CountryRepository countryRepository) {
    this.provinceMapper = provinceMapper;
    this.countryMapper = countryMapper;
    this.countryRepository = countryRepository;
  }

  public AddressResponseV1 map(Address address) {
    if (address == null) {
      return null;
    }

    final AddressResponseV1Builder builder = AddressResponseV1.builder();
    address.getLine1().ifPresent(builder::withLine1);
    address.getLine2().ifPresent(builder::withLine2);
    address.getLine3().ifPresent(builder::withLine3);
    address.getPostalCode().ifPresent(builder::withPostalCode);
    address.getCity().ifPresent(builder::withCity);
    address.getProvince().ifPresent(province -> builder.withProvince(provinceMapper.map(province)));
    address.getCountry()
           .ifPresent(country -> builder.withCountry(countryMapper.mapToAddressCountryResponseV1(country)));

    return builder.build();
  }

  public Address map(AddressSaveRequestV1 addressSaveRequestV1) {
    if (addressSaveRequestV1 == null) {
      return null;
    }

    AddressBuilder builder = Address.builder();
    addressSaveRequestV1.getLine1().ifPresent(builder::withLine1);
    addressSaveRequestV1.getLine2().ifPresent(builder::withLine2);
    addressSaveRequestV1.getLine3().ifPresent(builder::withLine3);
    addressSaveRequestV1.getPostalCode().ifPresent(builder::withPostalCode);
    addressSaveRequestV1.getCity().ifPresent(builder::withCity);
    addressSaveRequestV1.getCountryId().ifPresent(countryId -> {
      Country country = countryRepository.findById(countryId)
                                      .orElseThrow(() -> new NotFoundException(String.format(
                                          "Can't find Country with %s",
                                          countryId)));
      builder.withCountry(country);
      addressSaveRequestV1.getProvinceId()
                          .ifPresent(provinceId -> builder.withProvince(country.getProvinces()
                                                                               .stream()
                                                                               .filter(province -> province.getId()
                                                                                                           .equals(
                                                                                                               provinceId))
                                                                               .findAny()
                                                                               .orElseThrow(() -> new NotFoundException(
                                                                                   String.format(
                                                                                       "Can't find Province with %s",
                                                                                       provinceId)))));
    });

    addressSaveRequestV1.getProvinceId().ifPresent(provinceId -> addressSaveRequestV1.getCountryId()
                                                                                   .orElseThrow(() -> new NotFoundException(String.format("Can't find Province with %s",
                                                                               provinceId))));
    return builder.build();
  }
}
