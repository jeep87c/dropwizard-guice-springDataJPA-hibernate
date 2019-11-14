package com.dropwizard.seed.modules.absence.bootstrapper;

import com.dropwizard.seed.core.bootstrapper.EntityBootstrapper;
import com.dropwizard.seed.modules.absence.bootstrapper.entity.AbsenceEntityBootstrapper;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class AbsenceBootstrapperModule extends AbstractModule {

  @Override
  public void configure() {
    Multibinder<EntityBootstrapper> bootstrapperMultibinder = Multibinder.newSetBinder(binder(),
                                                                                       EntityBootstrapper.class);
    bootstrapperMultibinder.addBinding().to(AbsenceEntityBootstrapper.class);
  }
}
