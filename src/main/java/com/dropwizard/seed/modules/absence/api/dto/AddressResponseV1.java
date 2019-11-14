package com.dropwizard.seed.modules.absence.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Objects;
import java.util.Optional;

@JsonDeserialize(builder = AddressResponseV1.AddressResponseV1Builder.class)
public class AddressResponseV1 {

  private final Optional<String> line1;
  private final Optional<String> line2;
  private final Optional<String> line3;
  private final Optional<String> city;
  private final Optional<String> postalCode;
  private final Optional<ProvinceResponseV1> province;
  private final Optional<AddressCountryResponseV1> country;

  private AddressResponseV1(AddressResponseV1Builder builder) {
    line1 = builder.line1;
    line2 = builder.line2;
    line3 = builder.line3;
    city = builder.city;
    province = builder.province;
    postalCode = builder.postalCode;
    country = builder.country;
  }

  public static AddressResponseV1Builder builder() {
    return new AddressResponseV1Builder();
  }

  public Optional<String> getLine1() {
    return line1;
  }

  public Optional<String> getLine2() {
    return line2;
  }

  public Optional<String> getLine3() {
    return line3;
  }

  public Optional<String> getCity() {
    return city;
  }

  public Optional<String> getPostalCode() {
    return postalCode;
  }

  public Optional<ProvinceResponseV1> getProvince() {
    return province;
  }

  public Optional<AddressCountryResponseV1> getCountry() {
    return country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressResponseV1 that = (AddressResponseV1) o;
    return Objects.equals(line1, that.line1) && Objects.equals(line2, that.line2) && Objects.equals(line3, that.line3)
           && Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode) && Objects.equals(province,
                                                                                                               that.province)
           && Objects.equals(country, that.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(line1, line2, line3, city, postalCode, province, country);
  }

  @JsonPOJOBuilder
  public static class AddressResponseV1Builder {

    private Optional<String> line1 = Optional.empty();
    private Optional<String> line2 = Optional.empty();
    private Optional<String> line3 = Optional.empty();
    private Optional<String> city = Optional.empty();
    private Optional<ProvinceResponseV1> province = Optional.empty();
    private Optional<String> postalCode = Optional.empty();
    private Optional<AddressCountryResponseV1> country = Optional.empty();

    public AddressResponseV1Builder withLine1(String line1) {
      this.line1 = Optional.of(line1);
      return this;
    }

    public AddressResponseV1Builder withLine2(String line2) {
      this.line2 = Optional.of(line2);
      return this;
    }

    public AddressResponseV1Builder withLine3(String line3) {
      this.line3 = Optional.of(line3);
      return this;
    }

    public AddressResponseV1Builder withCity(String city) {
      this.city = Optional.of(city);
      return this;
    }

    public AddressResponseV1Builder withProvince(ProvinceResponseV1 province) {
      this.province = Optional.of(province);
      return this;
    }

    public AddressResponseV1Builder withPostalCode(String postalCode) {
      this.postalCode = Optional.of(postalCode);
      return this;
    }

    public AddressResponseV1Builder withCountry(AddressCountryResponseV1 country) {
      this.country = Optional.of(country);
      return this;
    }

    public AddressResponseV1 build() {
      return new AddressResponseV1(this);
    }
  }
}
