package com.dropwizard.seed;

import com.google.inject.Injector;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import javax.ws.rs.client.Client;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

public abstract class AbstractResourceAcceptanceTest {

  @ClassRule
  public static final DropwizardAppRule<WebserviceConfiguration> RULE = new DropwizardAppRule<>(
      WebserviceApplicationForTest.class,
      ResourceHelpers.resourceFilePath("dropwizard-config.yml"));

  static String BASE_URL;
  static final Clock clock = mock(Clock.class);
  static final UUID TEMPORARY_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

  private final WebserviceApplication application = RULE.getApplication();
  final Injector injector = application.getInjector();
  final Client client = new JerseyClientBuilder().register(
          JacksonObjectMapperFactory.buildJacksonJaxbJsonProvider()).build();

  @BeforeClass
  public static void baseSetUpClass() {
    BASE_URL = String.format("http://localhost:%d%s/",
                             RULE.getLocalPort(),
                             RULE.getEnvironment().getApplicationContext().getContextPath());
  }

  void setUpClock(Instant now) {
    reset(clock);
    given(clock.instant()).willReturn(now);
  }

  public static class WebserviceApplicationForTest extends WebserviceApplication {

    @Override
    protected Clock getClock() {
      given(clock.instant()).willAnswer(invocationOnMock -> Instant.now());
      return clock;
    }
  }
}
