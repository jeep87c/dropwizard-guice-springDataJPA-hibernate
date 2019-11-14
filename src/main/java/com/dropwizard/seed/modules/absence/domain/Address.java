package com.dropwizard.seed.modules.absence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Address {

  private final Optional<String> line1;
  private final Optional<String> line2;
  private final Optional<String> line3;
  private final Optional<String> city;
  private final Optional<Province> province;
  private final Optional<String> postalCode;
  private final Optional<Country> country;

  private Address(AddressBuilder builder) {
    line1 = builder.line1;
    line2 = builder.line2;
    line3 = builder.line3;
    city = builder.city;
    province = builder.province;
    postalCode = builder.postalCode;
    country = builder.country;
  }

  public static AddressBuilder builder() {
    return new AddressBuilder();
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

  public String getLines() {
    List<String> addressLines = new ArrayList<>();
    line1.ifPresent(addressLines::add);
    line2.ifPresent(addressLines::add);
    line3.ifPresent(addressLines::add);

    return String.join(" ", addressLines);
  }

  public Optional<String> getCity() {
    return city;
  }

  public Optional<Province> getProvince() {
    return province;
  }

  public Optional<String> getPostalCode() {
    return postalCode;
  }

  public Optional<Country> getCountry() {
    return country;
  }

  public static class AddressBuilder {

    private Optional<String> line1 = Optional.empty();
    private Optional<String> line2 = Optional.empty();
    private Optional<String> line3 = Optional.empty();
    private Optional<String> city = Optional.empty();
    private Optional<Province> province = Optional.empty();
    private Optional<String> postalCode = Optional.empty();
    private Optional<Country> country = Optional.empty();

    public AddressBuilder withLine1(String line1) {
      this.line1 = Optional.of(line1);
      return this;
    }

    public AddressBuilder withLine2(String line2) {
      this.line2 = Optional.of(line2);
      return this;
    }

    public AddressBuilder withLine3(String line3) {
      this.line3 = Optional.of(line3);
      return this;
    }

    public AddressBuilder withCity(String city) {
      this.city = Optional.of(city);
      return this;
    }

    public AddressBuilder withProvince(Province province) {
      this.province = Optional.of(province);
      return this;
    }

    public AddressBuilder withPostalCode(String postalCode) {
      this.postalCode = Optional.of(postalCode);
      return this;
    }

    public AddressBuilder withCountry(Country country) {
      this.country = Optional.of(country);
      return this;
    }

    public Address build() {
      return new Address(this);
    }
  }
}
