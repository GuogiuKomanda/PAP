package lt.pap.service;

import java.util.List;

import lt.pap.dao.ModelEngineRepository;
import lt.pap.model.Engine;
import lt.pap.model.Model;
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

public List<ModelEngine> findAll()
{
    return modelEngineRepository.findAll();
}

public ModelEngine findOne(Long id)
{
    return modelEngineRepository.findOne(id);
}

public ModelEngine findByModelAndEngine(Model model, Engine engine)
{
    return modelEngineRepository.findByModelAndEngine(model, engine);
}


}
