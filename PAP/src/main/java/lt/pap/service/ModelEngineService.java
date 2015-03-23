package lt.pap.service;

import lt.pap.dao.ModelEngineRepository;
import lt.pap.model.ModelEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModelEngineService {
  @Autowired
  private ModelEngineRepository modelEngineRepository;

  public ModelEngine save(ModelEngine me) {
    return modelEngineRepository.save(me);
  }


}
