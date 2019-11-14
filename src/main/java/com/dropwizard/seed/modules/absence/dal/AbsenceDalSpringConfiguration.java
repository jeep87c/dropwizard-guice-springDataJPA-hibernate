package com.dropwizard.seed.modules.absence.dal;

import com.google.common.collect.Sets;
import java.util.Set;

public class AbsenceDalSpringConfiguration {

  public static final Set<String> PACKAGES_TO_SCAN = Sets.newHashSet(
      "com.dropwizard.seed.modules.absence.dal.dao",
      "com.dropwizard.seed.modules.absence.dal.entity");
}
