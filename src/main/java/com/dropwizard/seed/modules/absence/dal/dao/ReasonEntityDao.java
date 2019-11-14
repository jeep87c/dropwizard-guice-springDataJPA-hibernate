package com.dropwizard.seed.modules.absence.dal.dao;

import com.dropwizard.seed.modules.absence.dal.entity.ReasonEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ReasonEntityDao extends CrudRepository<ReasonEntity, UUID> {

}
