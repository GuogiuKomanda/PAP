package lt.pap.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import lt.pap.model.Model;
import lt.pap.service.ModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ModelsBean {
  
  @Autowired
  private ModelService modelService;

  private List<Model> modelList;
  
  @PostConstruct
  private void init() {
    modelList = modelService.findAll();
  }

  public List<Model> getModelList() {
    return modelList;
  }

  public void setModelList(List<Model> modelList) {
    this.modelList = modelList;
  }
  
}
