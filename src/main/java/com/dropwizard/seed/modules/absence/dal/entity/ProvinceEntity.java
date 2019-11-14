package com.dropwizard.seed.modules.absence.dal.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
public class ProvinceEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @NotBlank
  private String name;

  @ManyToOne
  @JoinColumn(name = "country_id", insertable = false, updatable = false)
  private CountryEntity country;

  // Required by Hibernate
  @SuppressWarnings("unused")
  private ProvinceEntity() {
  }

  private ProvinceEntity(ProvinceEntityBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  public static ProvinceEntityBuilder builder(String name) {
    return builder(null, name);
  }

  public static ProvinceEntityBuilder builder(UUID id, String name) {
    return new ProvinceEntityBuilder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static class ProvinceEntityBuilder {

    private final UUID id;
    private final String name;

    private ProvinceEntityBuilder(UUID id, String name) {
      this.id = id;
      this.name = name;
    }

    public ProvinceEntity build() {
      return new ProvinceEntity(this);
    }
  }
}
