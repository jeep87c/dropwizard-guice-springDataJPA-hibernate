package com.dropwizard.seed.modules.absence.api.dto;

import com.dropwizard.seed.modules.absence.api.dto.ProvinceResponseV1.ProvinceResponseV1Builder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(builder = ProvinceResponseV1Builder.class)
public class ProvinceResponseV1 {

  private final UUID id;
  private final String name;

  private ProvinceResponseV1(ProvinceResponseV1Builder builder) {
    id = builder.id;
    name = builder.name;
  }

  public static ProvinceResponseV1Builder builder(UUID id, String name) {
    return new ProvinceResponseV1Builder(id, name);
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
    ProvinceResponseV1 that = (ProvinceResponseV1) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @JsonPOJOBuilder
  public static class ProvinceResponseV1Builder {

    private final UUID id;
    private final String name;

    private ProvinceResponseV1Builder(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
      this.id = id;
      this.name = name;
    }

    public ProvinceResponseV1 build() {
      return new ProvinceResponseV1(this);
    }
  }
}
