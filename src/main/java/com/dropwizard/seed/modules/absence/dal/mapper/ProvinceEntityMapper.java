package com.dropwizard.seed.modules.absence.dal.mapper;

import com.dropwizard.seed.modules.absence.dal.entity.ProvinceEntity;
import com.dropwizard.seed.modules.absence.domain.Province;

class ProvinceEntityMapper {

  public Province map(ProvinceEntity entity) {
    if (entity == null) {
      return null;
    }

    return Province.builder(entity.getId(), entity.getName()).build();
  }

  public ProvinceEntity map(Province province) {
    if (province == null) {
      return null;
    }

    return ProvinceEntity.builder(province.getId(), province.getName()).build();
  }
}
