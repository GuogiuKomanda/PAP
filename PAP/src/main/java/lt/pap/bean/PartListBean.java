package lt.pap.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import lt.pap.model.Part;
import lt.pap.service.PartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class PartListBean {
  @Autowired
  private PartService partService;

  private List<Part> partList;

  @PostConstruct
  void init() {
    partList = partService.findAll();
  }

  public List<Part> getPartList() {
    return partList;
  }
}
