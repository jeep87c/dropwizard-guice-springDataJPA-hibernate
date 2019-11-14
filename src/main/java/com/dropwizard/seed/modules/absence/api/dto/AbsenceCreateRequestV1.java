package com.dropwizard.seed.modules.absence.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(builder = AbsenceCreateRequestV1.AbsenceCreateRequestV1Builder.class)
public class AbsenceCreateRequestV1 {

  @NotNull
  private final Instant startsOn;
  @NotNull
  private final Instant endsOn;
  @NotNull
  private final UUID reasonId;
  private final Map<Instant, Float> hours;
  private final boolean usingSickBank;
  private final boolean paid;
  private final Optional<String> comment;

  private AbsenceCreateRequestV1(AbsenceCreateRequestV1Builder builder) {
    startsOn = builder.startsOn;
    endsOn = builder.endsOn;
    reasonId = builder.reasonId;
    hours = builder.hours;
    usingSickBank = builder.usingSickBank;
    paid = builder.paid;
    comment = builder.comment;
  }

  public static AbsenceCreateRequestV1Builder builder(Instant start,
                                                      Instant end,
                                                      UUID reasonId,
                                                      Map<Instant, Float> hours,
                                                      boolean usingSickBank,
                                                      boolean paid) {
    return new AbsenceCreateRequestV1Builder(start, end, reasonId, hours, usingSickBank, paid);
  }

  public Instant getStartsOn() {
    return startsOn;
  }

  public Instant getEndsOn() {
    return endsOn;
  }

  public UUID getReasonId() {
    return reasonId;
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
  public static class AbsenceCreateRequestV1Builder {

    private final Instant startsOn;
    private final Instant endsOn;
    private final UUID reasonId;
    private final Map<Instant, Float> hours;
    private final boolean usingSickBank;
    private final boolean paid;
    private Optional<String> comment = Optional.empty();

    private AbsenceCreateRequestV1Builder(@JsonProperty("startsOn") Instant start,
                                          @JsonProperty("endsOn") Instant end,
                                          @JsonProperty("reasonId") UUID reasonId,
                                          @JsonProperty("hours") Map<Instant, Float> hours,
                                          @JsonProperty("usingSickBank") boolean usingSickBank,
                                          @JsonProperty("paid") boolean paid) {
      this.startsOn = start;
      this.endsOn = end;
      this.reasonId = reasonId;
      this.hours = hours;
      this.usingSickBank = usingSickBank;
      this.paid = paid;
    }

    public AbsenceCreateRequestV1Builder withComment(String comment) {
      this.comment = Optional.ofNullable(comment);
      return this;
    }

    public AbsenceCreateRequestV1 build() {
      return new AbsenceCreateRequestV1(this);
    }
  }
}
