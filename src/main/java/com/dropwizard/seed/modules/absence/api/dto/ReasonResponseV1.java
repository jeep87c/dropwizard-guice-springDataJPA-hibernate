package com.dropwizard.seed.modules.absence.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(builder = ReasonResponseV1.ReasonResponseV1Builder.class)
public class ReasonResponseV1 {

  private final UUID id;
  private final String name;

  private ReasonResponseV1(ReasonResponseV1Builder builder) {
    id = builder.id;
    name = builder.name;
  }

  public static ReasonResponseV1Builder builder(UUID id, String name) {
    if (id == null || name == null) {
      throw new IllegalStateException();
    }
    return new ReasonResponseV1Builder(id, name);
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
    if (!(o instanceof ReasonResponseV1)) {
      return false;
    }
    ReasonResponseV1 that = (ReasonResponseV1) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @JsonPOJOBuilder
  public static class ReasonResponseV1Builder {

    private final UUID id;
    private final String name;

    private ReasonResponseV1Builder(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
      this.id = id;
      this.name = name;
    }

    public ReasonResponseV1 build() {
      return new ReasonResponseV1(this);
    }
  }
}
