package com.dropwizard.seed.modules.absence;

import com.dropwizard.seed.modules.absence.dal.dao.AbsenceEntityDao;
import com.dropwizard.seed.modules.absence.dal.dao.ReasonEntityDao;
import com.dropwizard.seed.modules.absence.dal.repository.AbsenceRepository;
import com.dropwizard.seed.modules.absence.dal.repository.JpaAbsenceRepository;
import com.dropwizard.seed.modules.absence.dal.repository.JpaReasonRepository;
import com.dropwizard.seed.modules.absence.dal.repository.ReasonRepository;
import com.google.inject.AbstractModule;
import org.springframework.beans.factory.BeanFactory;

public class AbsenceModule extends AbstractModule {

  private final BeanFactory beanFactory;

  public AbsenceModule(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  protected void configure() {
    bind(AbsenceEntityDao.class).toInstance(beanFactory.getBean(AbsenceEntityDao.class));
    bind(ReasonEntityDao.class).toInstance(beanFactory.getBean(ReasonEntityDao.class));
    bind(AbsenceRepository.class).to(JpaAbsenceRepository.class);
    bind(ReasonRepository.class).to(JpaReasonRepository.class);
  }
}
