package com.dropwizard.seed.modules.absence.dal.entity;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class ReasonEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @NotBlank
  @Unique
  private String name;

  // Required by Hibernate
  private ReasonEntity() {
  }

  private ReasonEntity(ReasonEntityBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  public static ReasonEntityBuilder builder(UUID id, String name) {
    if (id == null || name == null) {
      throw new IllegalStateException();
    }
    return new ReasonEntityBuilder(id, name);
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static class ReasonEntityBuilder {

    private final UUID id;
    private final String name;

    public ReasonEntityBuilder(UUID id, String name) {
      this.id = id;
      this.name = name;
    }

    public ReasonEntity build() {
      return new ReasonEntity(this);
    }
  }
}
