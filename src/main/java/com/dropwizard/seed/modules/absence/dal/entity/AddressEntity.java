package com.dropwizard.seed.modules.absence.dal.entity;

import java.util.Optional;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AddressEntity {

  private String line1;
  private String line2;
  private String line3;
  private String city;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "province_id")
  private ProvinceEntity province;

  private String postalCode;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country_id")
  private CountryEntity country;

  // Required by Hibernate
  @SuppressWarnings("unused")
  private AddressEntity() {
  }

  private AddressEntity(AddressEntityBuilder builder) {
    line1 = builder.line1;
    line2 = builder.line2;
    line3 = builder.line3;
    city = builder.city;
    province = builder.province;
    postalCode = builder.postalCode;
    country = builder.country;
  }

  public static AddressEntityBuilder builder() {
    return new AddressEntityBuilder();
  }

  public Optional<String> getLine1() {
    return Optional.ofNullable(line1);
  }

  public Optional<String> getLine2() {
    return Optional.ofNullable(line2);
  }

  public Optional<String> getLine3() {
    return Optional.ofNullable(line3);
  }

  public Optional<String> getCity() {
    return Optional.ofNullable(city);
  }

  public Optional<ProvinceEntity> getProvince() {
    return Optional.ofNullable(province);
  }

  public Optional<String> getPostalCode() {
    return Optional.ofNullable(postalCode);
  }

  public Optional<CountryEntity> getCountry() {
    return Optional.ofNullable(country);
  }

  public static class AddressEntityBuilder {

    private String line1;
    private String line2;
    private String line3;
    private String city;
    private ProvinceEntity province;
    private String postalCode;
    private CountryEntity country;

    public AddressEntityBuilder withLine1(String line1) {
      this.line1 = line1;
      return this;
    }

    public AddressEntityBuilder withLine2(String line2) {
      this.line2 = line2;
      return this;
    }

    public AddressEntityBuilder withLine3(String line3) {
      this.line3 = line3;
      return this;
    }

    public AddressEntityBuilder withCity(String city) {
      this.city = city;
      return this;
    }

    public AddressEntityBuilder withProvince(ProvinceEntity province) {
      this.province = province;
      return this;
    }

    public AddressEntityBuilder withPostalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public AddressEntityBuilder withCountry(CountryEntity country) {
      this.country = country;
      return this;
    }

    public AddressEntity build() {
      return new AddressEntity(this);
    }
  }
}
