package com.dropwizard.seed;

import static com.google.common.truth.Truth.assertThat;

import com.dropwizard.seed.modules.absence.api.dto.AbsenceCreateRequestV1;
import com.dropwizard.seed.modules.absence.dal.entity.ReasonEntity;
import com.dropwizard.seed.modules.absence.fixture.AbsenceEntityFixture;
import com.dropwizard.seed.modules.absence.fixture.ReasonEntityFixture;
import java.time.Instant;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.After;
import org.junit.Test;

public class AbsenceResourceAcceptanceTest extends AbstractResourceAcceptanceTest {

  private static final String ABSENCE_PATH = "absences";
  private static final Instant STARTS_ON = Instant.parse("2017-03-01T17:27:49.072881Z");
  private static final Instant ENDS_ON = Instant.parse("2018-03-01T17:27:49.072881Z");
  private static final Instant DATE_1 = Instant.parse("2017-03-02T17:27:49.072881Z");
  private static final Instant DATE_2 = Instant.parse("2017-03-03T17:27:49.072881Z");
  private static final Instant DATE_3 = Instant.parse("2017-03-04T17:27:49.072881Z");
  private static final Instant DATE_4 = Instant.parse("2017-03-05T17:27:49.072881Z");
  private static final float HOURS_1 = 10f;
  private static final float HOURS_2 = 20f;
  private static final float HOURS_3 = 11f;
  private static final float HOURS_4 = 19f;
  private static final Map<Instant, Float> MAP_HOURS = Map.of(DATE_1, HOURS_1, DATE_2, HOURS_2);
  private static final Map<Instant, Float> MAP_HOURS_2 = Map.of(DATE_3, HOURS_3, DATE_4, HOURS_4);
  private static final boolean USE_SICK_BANK = true;
  private static final boolean PAID = true;
  private static final String COMMENT = "Citrouille";
  private static final String REASON_NAME = "Pumpkin spice latte";

  private final AbsenceEntityFixture absenceEntityFixture = injector.getInstance(AbsenceEntityFixture.class);
  private final ReasonEntityFixture reasonEntityFixture = injector.getInstance(ReasonEntityFixture.class);

  @After
  public void dropData() {
    absenceEntityFixture.deleteAll();
    reasonEntityFixture.deleteAll();
  }

  @Test
  public void rollbackTest() {
    // given
    ReasonEntity reasonEntity = reasonEntityFixture.givenReasonEntity(ReasonEntity.builder(TEMPORARY_ID, REASON_NAME));
    AbsenceCreateRequestV1 absenceCreateRequestV1 = AbsenceCreateRequestV1.builder(STARTS_ON,
                                                                                   ENDS_ON,
                                                                                   reasonEntity.getId(),
                                                                                   MAP_HOURS,
                                                                                   USE_SICK_BANK,
                                                                                   PAID).withComment(COMMENT).build();

    // when
    Response response = client.target(String.format(BASE_URL + ABSENCE_PATH, RULE.getLocalPort())).request().post(Entity
                                                                                                                      .json(
                                                                                                                          absenceCreateRequestV1));

    // then
    assertThat(response.getStatus()).isEqualTo(Status.CONFLICT.getStatusCode());
    assertThat(absenceEntityFixture.findAll()).isEmpty();
  }
}
