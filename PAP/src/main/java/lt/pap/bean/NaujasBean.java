package lt.pap.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import lt.pap.model.FuelType;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.service.FuelTypeService;
import lt.pap.service.MakeService;
import lt.pap.service.ModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class NaujasBean {
  @Autowired
  private MakeService makeService;

  @Autowired
  private ModelService modelService;

  @Autowired
  private FuelTypeService fueltypeService;

  private Make selectedMake;

  private Model selectedModel;

  private FuelType selectedFuelType;

  private List<Make> availableMakeList;

  private List<Model> availableModelList;

  private List<FuelType> availableFuelTypeList;

  @PostConstruct
  private void init() {
    availableMakeList = makeService.findAll();
    availableModelList = modelService.findAll();
    availableFuelTypeList = fueltypeService.findAll();

    // selectedMake = availableMakeList.get(2);
  }

  public void updateModelList() {
    if(selectedMake!= null)
      availableModelList = modelService.findByMakeId(selectedMake.getId());
  }
  
  public List<Make> getAvailableMakeList() {
    return availableMakeList;
  }

  public void setAvailableMakeList(List<Make> availableMakeList) {
    this.availableMakeList = availableMakeList;
  }

  public List<Model> getAvailableModelList() {
    return availableModelList;
  }

  public void setAvailableModelList(List<Model> availableModelList) {
    this.availableModelList = availableModelList;
  }

  public List<FuelType> getAvailableFuelTypeList() {
    return availableFuelTypeList;
  }

  public void setAvailableFuelTypeList(List<FuelType> availableFuelTypeList) {
    this.availableFuelTypeList = availableFuelTypeList;
  }

  public Make getSelectedMake() {
    return selectedMake;
  }

  public void setSelectedMake(Make selectedMake) {
    this.selectedMake = selectedMake;
  }

  public Model getSelectedModel() {
    return selectedModel;
  }

  public void setSelectedModel(Model selectedModel) {
    this.selectedModel = selectedModel;
  }

  public FuelType getSelectedFuelType() {
    return selectedFuelType;
  }

  public void setSelectedFuelType(FuelType selectedFuelType) {
    this.selectedFuelType = selectedFuelType;
  }
}
