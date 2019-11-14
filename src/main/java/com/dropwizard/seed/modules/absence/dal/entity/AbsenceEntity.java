package com.dropwizard.seed.modules.absence.dal.entity;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
public class AbsenceEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @NotNull
  private Instant startsOn;

  @NotNull
  private Instant endsOn;

  @ManyToOne
  @JoinColumn(name = "reason_id")
  private ReasonEntity reason;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "ABSENCE_DATES_MAPPING",
                   joinColumns = {@JoinColumn(name = "absence_id", referencedColumnName = "id")})
  @MapKeyColumn(name = "date")
  @Column(name = "hours")
  private Map<Instant, Float> hours;

  private boolean usingSickBank;
  private boolean paid;
  private String comment;

  // Required by Hibernate
  private AbsenceEntity() {
  }

  private AbsenceEntity(AbsenceEntityBuilder builder) {
    id = builder.id;
    startsOn = builder.startsOn;
    endsOn = builder.endsOn;
    reason = builder.reason;
    hours = builder.hours;
    usingSickBank = builder.usingSickBank;
    paid = builder.paid;
    comment = builder.comment;
  }

  public static AbsenceEntityBuilder builder(UUID id,
                                             Instant start,
                                             Instant end,
                                             ReasonEntity reason,
                                             Map<Instant, Float> hours,
                                             boolean usingSickBank,
                                             boolean paid) {
    if (id == null || start == null || end == null || reason == null || hours == null) {

      throw new IllegalStateException("Id, employee id, start date, end date and reason are required for Absence");
    }

    return new AbsenceEntityBuilder(id, start, end, reason, hours, usingSickBank, paid);
  }

  public UUID getId() {
    return id;
  }

  public Instant getStartsOn() {
    return startsOn;
  }

  public Instant getEndsOn() {
    return endsOn;
  }

  public ReasonEntity getReason() {
    return reason;
  }

  public Map<Instant, Float> getHours() {
    return hours;
  }

  public boolean isUsingSickBank() {
    return usingSickBank;
  }

  public boolean isPaid() {
    return paid;
  }

  public Optional<String> getComment() {
    return Optional.ofNullable(comment);
  }

  public static class AbsenceEntityBuilder {

    private final UUID id;
    private final Instant startsOn;
    private final Instant endsOn;
    private final ReasonEntity reason;
    private final Map<Instant, Float> hours;
    private final boolean usingSickBank;
    private final boolean paid;
    private String comment;

    AbsenceEntityBuilder(UUID id,
                         Instant start,
                         Instant end,
                         ReasonEntity reason,
                         Map<Instant, Float> hours,
                         boolean usingSickBank,
                         boolean paid) {
      this.id = id;
      this.startsOn = start;
      this.endsOn = end;
      this.reason = reason;
      this.hours = hours;
      this.usingSickBank = usingSickBank;
      this.paid = paid;
    }

    public AbsenceEntityBuilder withComment(String comment) {
      this.comment = comment;
      return this;
    }

    public AbsenceEntity build() {
      return new AbsenceEntity(this);
    }
  }
}
