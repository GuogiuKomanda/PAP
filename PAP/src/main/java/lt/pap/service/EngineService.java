package lt.pap.service;

import lt.pap.dao.EngineRepository;
import lt.pap.model.Engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EngineService
{
    @Autowired
    private EngineRepository engineRepository;
    
    public Engine save(Engine engine) {
      return engineRepository.save(engine);
    }
}
