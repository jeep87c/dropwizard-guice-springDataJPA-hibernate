package com.dropwizard.seed.core.dal.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityDao<T> extends JpaRepository<T, UUID> {

}
