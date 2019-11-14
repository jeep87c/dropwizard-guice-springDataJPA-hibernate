package com.dropwizard.seed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import io.dropwizard.jackson.Jackson;

class JacksonObjectMapperFactory {

  static ObjectMapper buildJacksonObjectMapper() {
    ObjectMapper objectMapper = Jackson.newObjectMapper();
    objectMapper.findAndRegisterModules();
    return objectMapper;
  }

  static JacksonJaxbJsonProvider buildJacksonJaxbJsonProvider() {
    JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
    jacksonJaxbJsonProvider.setMapper(JacksonObjectMapperFactory.buildJacksonObjectMapper());
    return jacksonJaxbJsonProvider;
  }
}
