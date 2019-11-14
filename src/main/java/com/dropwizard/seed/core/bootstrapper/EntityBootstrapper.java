package com.dropwizard.seed.core.bootstrapper;

import java.util.UUID;

public interface EntityBootstrapper {
  UUID TEMPORARY_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

  void seed();
}
