package com.dropwizard.seed.modules.absence.api.mapper;

import com.dropwizard.seed.modules.absence.api.dto.ProvinceResponseV1;
import com.dropwizard.seed.modules.absence.domain.Province;

class ProvinceMapper {

  public ProvinceResponseV1 map(Province province) {
    if (province == null) {
      return null;
    }

    return ProvinceResponseV1.builder(province.getId(), province.getName()).build();
  }
}
