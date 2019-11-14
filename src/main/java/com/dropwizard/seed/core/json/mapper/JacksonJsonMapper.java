package com.dropwizard.seed.core.json.mapper;

import com.dropwizard.seed.core.json.exception.JsonMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonMapper implements JsonMapper {

  private final ObjectMapper objectMapper;

  public JacksonJsonMapper() {
    this.objectMapper = new ObjectMapper();
  }

  public String writeObjectAsJsonString(Object object) throws JsonMapperException {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException exception) {
      throw new JsonMapperException();
    }
  }
}
