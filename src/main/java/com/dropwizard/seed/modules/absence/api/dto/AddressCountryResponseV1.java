package com.dropwizard.seed.modules.absence.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(builder = AddressCountryResponseV1.AddressCountryResponseV1Builder.class)
public class AddressCountryResponseV1 {

  private final UUID id;
  private final String name;

  private AddressCountryResponseV1(AddressCountryResponseV1Builder builder) {
    id = builder.id;
    name = builder.name;
  }

  public static AddressCountryResponseV1Builder builder(UUID id, String name) {
    return new AddressCountryResponseV1Builder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressCountryResponseV1 that = (AddressCountryResponseV1) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @JsonPOJOBuilder
  public static class AddressCountryResponseV1Builder {

    private final UUID id;
    private final String name;

    private AddressCountryResponseV1Builder(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
      this.id = id;
      this.name = name;
    }

    public AddressCountryResponseV1 build() {
      return new AddressCountryResponseV1(this);
    }
  }
}
