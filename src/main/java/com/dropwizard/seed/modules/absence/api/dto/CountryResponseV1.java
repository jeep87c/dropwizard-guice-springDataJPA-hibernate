package com.dropwizard.seed.modules.absence.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonDeserialize(builder = CountryResponseV1.CountryResponseV1Builder.class)
public class CountryResponseV1 {

  private final UUID id;
  private final String name;
  private final Set<ProvinceResponseV1> provinces;

  private CountryResponseV1(CountryResponseV1Builder builder) {
    id = builder.id;
    name = builder.name;
    provinces = builder.provinces;
  }

  public static CountryResponseV1Builder builder(UUID id, String name) {
    return new CountryResponseV1Builder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<ProvinceResponseV1> getProvinces() {
    return provinces;
  }

  @JsonPOJOBuilder
  public static class CountryResponseV1Builder {

    private final UUID id;
    private final String name;
    private Set<ProvinceResponseV1> provinces = new HashSet<>();

    private CountryResponseV1Builder(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
      this.id = id;
      this.name = name;
    }

    public CountryResponseV1Builder withProvinces(Set<ProvinceResponseV1> provinces) {
      this.provinces = provinces;
      return this;
    }

    public CountryResponseV1 build() {
      return new CountryResponseV1(this);
    }
  }
}
