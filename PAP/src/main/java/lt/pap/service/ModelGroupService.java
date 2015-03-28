package lt.pap.service;

import java.util.List;

import lt.pap.dao.ModelGroupRepository;
import lt.pap.model.ModelGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ModelGroupService
{
    @Autowired
    private ModelGroupRepository modelgroupRepository;
    
    public List<ModelGroup> findAll()
    {
        return modelgroupRepository.findAll();
    }
    
    public ModelGroup findOne(Long id)
    {
        return modelgroupRepository.findOne(id);
    }
    
    public ModelGroup save(ModelGroup modelgroup)
    {
        return modelgroupRepository.save(modelgroup);
    }
    
    public List<ModelGroup> findByMakeId(Long id)
    {
        return modelgroupRepository.findByMakeId(id);
    }
    
}
