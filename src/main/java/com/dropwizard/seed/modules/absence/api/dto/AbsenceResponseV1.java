package com.dropwizard.seed.modules.absence.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(builder = AbsenceResponseV1.AbsenceResponseV1Builder.class)
public class AbsenceResponseV1 {

  private final UUID id;
  private final Instant startsOn;
  private final Instant endsOn;
  private final ReasonResponseV1 reason;
  private final Map<Instant, Float> hours;
  private final boolean usingSickBank;
  private final boolean paid;
  private final Optional<String> comment;

  private AbsenceResponseV1(AbsenceResponseV1Builder builder) {
    id = builder.id;
    startsOn = builder.startsOn;
    endsOn = builder.endsOn;
    reason = builder.reason;
    hours = builder.hours;
    usingSickBank = builder.usingSickBank;
    paid = builder.paid;
    comment = builder.comment;
  }

  public static AbsenceResponseV1Builder builder(UUID id,
                                                 Instant start,
                                                 Instant end,
                                                 ReasonResponseV1 reason,
                                                 Map<Instant, Float> hours,
                                                 boolean usingSickBank,
                                                 boolean paid) {
    if (id == null || start == null || end == null || reason == null
        || hours == null) {
      throw new IllegalStateException("Id, company profile, start date, end date and reason are required for Absence");
    }

    return new AbsenceResponseV1Builder(id,
                                        start,
                                        end,
                                        reason,
                                        hours,
                                        usingSickBank,
                                        paid);
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

  public ReasonResponseV1 getReason() {
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

  @JsonPOJOBuilder
  public static class AbsenceResponseV1Builder {

    private final UUID id;
    private final Instant startsOn;
    private final Instant endsOn;
    private final ReasonResponseV1 reason;
    private final Map<Instant, Float> hours;
    private final boolean usingSickBank;
    private final boolean paid;
    private Optional<String> comment = Optional.empty();

    AbsenceResponseV1Builder(@JsonProperty("id") UUID id,
                             @JsonProperty("startsOn") Instant start,
                             @JsonProperty("endsOn") Instant end,
                             @JsonProperty("reason") ReasonResponseV1 reason,
                             @JsonProperty("hours") Map<Instant, Float> hours,
                             @JsonProperty("usingSickBank") boolean usingSickBank,
                             @JsonProperty("paid") boolean paid) {
      this.id = id;
      this.startsOn = start;
      this.endsOn = end;
      this.reason = reason;
      this.hours = hours;
      this.usingSickBank = usingSickBank;
      this.paid = paid;
    }

    public AbsenceResponseV1Builder withComment(String comment) {
      this.comment = Optional.ofNullable(comment);
      return this;
    }

    public AbsenceResponseV1 build() {
      return new AbsenceResponseV1(this);
    }
  }
}
