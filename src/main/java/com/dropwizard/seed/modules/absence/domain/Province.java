package com.dropwizard.seed.modules.absence.domain;

import java.util.UUID;

public class Province {

  private final UUID id;
  private final String name;

  private Province(ProvinceBuilder builder) {
    id = builder.id;
    name = builder.name;
  }

  public static ProvinceBuilder builder(UUID id, String name) {
    return new ProvinceBuilder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static class ProvinceBuilder {

    private final UUID id;
    private final String name;

    private ProvinceBuilder(UUID id, String name) {
      this.id = id;
      this.name = name;
    }

    public Province build() {
      return new Province(this);
    }
  }
}
