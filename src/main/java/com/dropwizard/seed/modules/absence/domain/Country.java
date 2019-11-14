package com.dropwizard.seed.modules.absence.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Country {

  private final UUID id;
  private final String name;
  private final Set<Province> provinces;

  private Country(CountryBuilder builder) {
    id = builder.id;
    name = builder.name;
    provinces = builder.provinces;
  }

  public static CountryBuilder builder(UUID id, String name) {
    return new CountryBuilder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Province> getProvinces() {
    return provinces;
  }

  public static class CountryBuilder {

    private final UUID id;
    private final String name;
    private Set<Province> provinces = new HashSet<>();

    private CountryBuilder(UUID id, String name) {
      this.id = id;
      this.name = name;
    }

    public CountryBuilder setProvinces(Set<Province> provinces) {
      this.provinces = provinces;
      return this;
    }

    public Country build() {
      return new Country(this);
    }
  }
}
