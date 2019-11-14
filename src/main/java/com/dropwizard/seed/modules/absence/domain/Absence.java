package com.dropwizard.seed.modules.absence.domain;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Absence {

  private final UUID id;
  private final Instant startsOn;
  private final Instant endsOn;
  private final Reason reason;
  private final Map<Instant, Float> hours;
  private final boolean usingSickBank;
  private final boolean paid;
  private final Optional<String> comment;

  private Absence(AbsenceBuilder builder) {
    id = builder.id;
    startsOn = builder.startsOn;
    endsOn = builder.endsOn;
    reason = builder.reason;
    hours = builder.hours;
    usingSickBank = builder.usingSickBank;
    paid = builder.paid;
    comment = builder.comment;
  }

  public static AbsenceBuilder builder(UUID id,
                                       Instant startsOn,
                                       Instant endsOn,
                                       Reason reason,
                                       Map<Instant, Float> hours,
                                       boolean usingSickBank,
                                       boolean paid) {
    if (id == null || startsOn == null || endsOn == null || reason == null) {
      throw new IllegalStateException("Id, start date, end date and reason are required for Absence");
    }
    return new AbsenceBuilder(id, startsOn, endsOn, reason, hours, usingSickBank, paid);
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

  public Reason getReason() {
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
    return comment;
  }

  public static class AbsenceBuilder {

    private final UUID id;
    private final Instant startsOn;
    private final Instant endsOn;
    private final Reason reason;
    private final Map<Instant, Float> hours;
    private final boolean usingSickBank;
    private final boolean paid;
    private Optional<String> comment = Optional.empty();

    private AbsenceBuilder(UUID id,
                           Instant start,
                           Instant end,
                           Reason reason,
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

    public AbsenceBuilder withComment(String comment) {
      this.comment = Optional.ofNullable(comment);
      return this;
    }

    public Absence build() {
      return new Absence(this);
    }
  }
}
