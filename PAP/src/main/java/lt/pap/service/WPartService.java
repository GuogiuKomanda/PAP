package lt.pap.service;

import java.util.List;

import lt.pap.dao.WPartRepository;
import lt.pap.model.WPart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WPartService
{   @Autowired
    private WPartRepository WPartRepository;

public List<WPart> findAll()
{
    return WPartRepository.findAll();
}

public WPart findOne(Long id)
{
    return WPartRepository.findOne(id);
}
    
}
