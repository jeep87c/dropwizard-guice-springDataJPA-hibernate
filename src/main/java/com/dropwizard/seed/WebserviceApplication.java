package com.dropwizard.seed;

import com.dropwizard.seed.core.json.JsonModule;
import com.dropwizard.seed.modules.absence.AbsenceModule;
import com.dropwizard.seed.modules.absence.api.resource.AbsenceResource;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Clock;
import java.util.EnumSet;

public class WebserviceApplication extends Application<WebserviceConfiguration> {

  public static void main(String[] args) throws Exception {
    new WebserviceApplication().run(args);
  }

  Clock getClock() {
    return Clock.systemUTC();
  }

  Injector getInjector() {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(DalSpringConfiguration.class).getBeanFactory();
    return Guice.createInjector(binder -> binder.bind(Clock.class).toInstance(getClock()),
                                new JsonModule(),
                                new AbsenceModule(beanFactory));
  }

  @Override
  public String getName() {
    return "Seed-API";
  }

  @Override
  public void initialize(Bootstrap<WebserviceConfiguration> bootstrap) {
    bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                                                                            new EnvironmentVariableSubstitutor(true)));

    bootstrap.setObjectMapper(JacksonObjectMapperFactory.buildJacksonObjectMapper()
                                                        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                                                                   false));
  }

  @Override
  public void run(WebserviceConfiguration configuration, Environment environment) {
    Injector injector = getInjector();

    if (EnumSet.of(Stage.DEV, Stage.STAGING).contains(configuration.getStage())) {
      bootstrapDatabase(injector);
    }

    environment.jersey().register(injector.getInstance(AbsenceResource.class));
  }

  private void bootstrapDatabase(Injector injector) {
    injector.createChildInjector(SupremeBootstrapper.getModules()).getInstance(SupremeBootstrapper.class).bootstrap();
  }
}
