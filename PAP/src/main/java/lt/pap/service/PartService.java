package lt.pap.service;

import java.util.List;

import lt.pap.dao.PartRepository;
import lt.pap.model.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PartService {

  @Autowired
  private PartRepository partRepository;

  public List<Part> findAll() {
    return partRepository.findAll();
  }

  public Part findOne(long id) {
    return partRepository.findOne(id);
  }

  public long count() {
    return partRepository.count();
  }

  public Part save(Part part) {
    return partRepository.save(part);
  }

  public boolean exists(Long partId) {
    return partRepository.exists(partId);
  }

  public void deleteAll() {
    partRepository.deleteAll();
  }
}
