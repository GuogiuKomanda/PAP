package lt.pap.service;

import java.util.List;

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

    public List<Engine> findAll()
    {
        return engineRepository.findAll();
    }

    public Engine findOne(Long id)
    {
        return engineRepository.findOne(id);
    }

    public Engine findByName(String name)
    {
        return engineRepository.findByName(name);
    }
    public Engine findByCode(String code)
    {
        return engineRepository.findByCode(code);
    }
    
//    public List<Engine> findBy
    
    
}
