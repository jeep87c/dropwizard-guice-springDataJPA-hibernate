package com.dropwizard.seed.modules.absence.domain;

import java.util.UUID;

public class Reason {

  private final UUID id;
  private final String name;

  private Reason(ReasonBuilder builder) {
    id = builder.id;
    name = builder.name;
  }

  public static ReasonBuilder builder(UUID id, String name) {
    if (id == null || name == null) {
      throw new IllegalStateException();
    }
    return new ReasonBuilder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static class ReasonBuilder {

    private final UUID id;
    private final String name;

    public ReasonBuilder(UUID id, String name) {
      this.id = id;
      this.name = name;
    }

    public Reason build() {
      return new Reason(this);
    }
  }
}
