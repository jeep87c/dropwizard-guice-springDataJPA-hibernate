package com.dropwizard.seed.modules.absence.api.dto;

import com.dropwizard.seed.modules.absence.api.dto.AddressSaveRequestV1.AddressSaveRequestV1Builder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(builder = AddressSaveRequestV1Builder.class)
public class AddressSaveRequestV1 {

  private final Optional<String> line1;
  private final Optional<String> line2;
  private final Optional<String> line3;
  private final Optional<String> city;
  private final Optional<String> postalCode;
  private final Optional<UUID> provinceId;
  private final Optional<UUID> countryId;

  private AddressSaveRequestV1(AddressSaveRequestV1Builder builder) {
    line1 = builder.line1;
    line2 = builder.line2;
    line3 = builder.line3;
    city = builder.city;
    postalCode = builder.postalCode;
    provinceId = builder.provinceId;
    countryId = builder.countryId;
  }

  public static AddressSaveRequestV1Builder builder() {
    return new AddressSaveRequestV1Builder();
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

  public Optional<UUID> getProvinceId() {
    return provinceId;
  }

  public Optional<UUID> getCountryId() {
    return countryId;
  }

  @JsonPOJOBuilder
  public static class AddressSaveRequestV1Builder {

    private Optional<String> line1 = Optional.empty();
    private Optional<String> line2 = Optional.empty();
    private Optional<String> line3 = Optional.empty();
    private Optional<String> city = Optional.empty();
    private Optional<String> postalCode = Optional.empty();
    private Optional<UUID> provinceId = Optional.empty();
    private Optional<UUID> countryId = Optional.empty();

    public AddressSaveRequestV1Builder withLine1(String line1) {
      this.line1 = Optional.ofNullable(line1);
      return this;
    }

    public AddressSaveRequestV1Builder withLine2(String line2) {
      this.line2 = Optional.ofNullable(line2);
      return this;
    }

    public AddressSaveRequestV1Builder withLine3(String line3) {
      this.line3 = Optional.ofNullable(line3);
      return this;
    }

    public AddressSaveRequestV1Builder withCity(String city) {
      this.city = Optional.ofNullable(city);
      return this;
    }

    public AddressSaveRequestV1Builder withPostalCode(String postalCode) {
      this.postalCode = Optional.ofNullable(postalCode);
      return this;
    }

    public AddressSaveRequestV1Builder withProvinceId(UUID provinceId) {
      this.provinceId = Optional.ofNullable(provinceId);
      return this;
    }

    public AddressSaveRequestV1Builder withCountryId(UUID countryId) {
      this.countryId = Optional.ofNullable(countryId);
      return this;
    }

    public AddressSaveRequestV1 build() {
      return new AddressSaveRequestV1(this);
    }
  }
}
