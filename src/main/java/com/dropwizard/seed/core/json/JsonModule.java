package com.dropwizard.seed.core.json;

import com.dropwizard.seed.core.json.mapper.JacksonJsonMapper;
import com.dropwizard.seed.core.json.mapper.JsonMapper;
import com.google.inject.AbstractModule;

public class JsonModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(JsonMapper.class).to(JacksonJsonMapper.class);
  }
}
