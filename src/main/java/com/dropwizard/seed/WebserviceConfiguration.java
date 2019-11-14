package com.dropwizard.seed;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class WebserviceConfiguration extends Configuration {

  @NotNull
  @Valid
  public Stage stage;

  @JsonProperty
  public Stage getStage() {
    return stage;
  }

}
