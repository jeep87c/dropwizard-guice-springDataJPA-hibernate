package com.dropwizard.seed.core.utils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetUtils {

  @SafeVarargs
  public static <T> Set<T> combine(Set<T>... sets) {
    Stream<T> stream = Stream.of();
    for (Set<T> e : sets) {
      stream = Stream.concat(stream, e.stream());
    }

    return stream.collect(Collectors.toSet());
  }
}
