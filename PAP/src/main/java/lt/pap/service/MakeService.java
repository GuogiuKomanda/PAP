package lt.pap.service;

import java.util.List;

import lt.pap.dao.MakeRepository;
import lt.pap.model.Make;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MakeService {
  @Autowired
  private MakeRepository makeRepository;

  public List<Make> findAll() {
    return makeRepository.findAll();
  }

  public Make findOne(Long id) {
    return makeRepository.findOne(id);
  }

  public Make save(Make make) {
    return makeRepository.save(make);
  }
  
  public Make findByName(String name) {
	    return makeRepository.findByName(name);
	  }
}
