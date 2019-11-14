package com.dropwizard.seed.core.json.mapper;

import com.dropwizard.seed.core.json.exception.JsonMapperException;

public interface JsonMapper  {

  String writeObjectAsJsonString(Object object) throws JsonMapperException;
}

