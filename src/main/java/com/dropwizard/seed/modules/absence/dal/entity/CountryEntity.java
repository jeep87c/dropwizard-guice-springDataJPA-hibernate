package com.dropwizard.seed.modules.absence.dal.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class CountryEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @NotBlank
  private String name;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "country_id")
  private Set<ProvinceEntity> provinces = new HashSet<>();

  // Required by Hibernate
  @SuppressWarnings("unused")
  private CountryEntity() {
  }

  private CountryEntity(CountryEntityBuilder builder) {
    id = builder.id;
    name = builder.name;
    provinces = builder.provinces;
  }

  public static CountryEntityBuilder builder(UUID id, String name) {
    return new CountryEntityBuilder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<ProvinceEntity> getProvinces() {
    return provinces;
  }

  public static class CountryEntityBuilder {

    private final UUID id;
    private final String name;
    private final Set<ProvinceEntity> provinces = new HashSet<>();

    private CountryEntityBuilder(UUID id, String name) {
      this.id = id;
      this.name = name;
    }

    public CountryEntityBuilder withProvinces(Set<ProvinceEntity> provinces) {
      this.provinces.addAll(provinces);
      return this;
    }

    public CountryEntity build() {
      return new CountryEntity(this);
    }
  }
}
