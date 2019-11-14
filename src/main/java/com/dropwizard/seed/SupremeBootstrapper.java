package com.dropwizard.seed;

import com.dropwizard.seed.core.bootstrapper.EntityBootstrapper;
import com.dropwizard.seed.modules.absence.bootstrapper.AbsenceBootstrapperModule;
import com.google.inject.Module;

import javax.inject.Inject;
import java.util.Set;

class SupremeBootstrapper {

  private final Set<EntityBootstrapper> bootstrappers;

  @Inject
  public SupremeBootstrapper(Set<EntityBootstrapper> bootstrappers) {
    this.bootstrappers = bootstrappers;
  }

  public static Set<? extends Module> getModules() {
    return Set.of(new AbsenceBootstrapperModule());
  }

  public void bootstrap() {
    bootstrappers.forEach(EntityBootstrapper::seed);
  }
}
