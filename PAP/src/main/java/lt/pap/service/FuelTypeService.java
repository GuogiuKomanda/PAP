package lt.pap.service;

import java.util.List;

import lt.pap.dao.FuelTypeRepository;
import lt.pap.model.FuelType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FuelTypeService
{
    @Autowired
    private FuelTypeRepository fueltypeRepository;

    public List<FuelType> findAll()
    {
        return fueltypeRepository.findAll();
    }

    public FuelType findOne(Long id)
    {
        return fueltypeRepository.findOne(id);
    }

    public FuelType save(FuelType fuel)
    {
        return fueltypeRepository.save(fuel);
    }
    
}
