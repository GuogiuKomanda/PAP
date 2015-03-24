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
public class NaujasBean
{
    @Autowired
    private MakeService makeService;
    
    private Make selectedMake;
    
    private List<Make> availableMakeList;
    @Autowired
    private ModelService modelService;
    
    private Model selectedModel;
    
    private List<Model> availableModelList;
    @Autowired
    private FuelTypeService fueltypeService;
    
    private FuelType selectedFuelType;
    
   private List<Make> availableFuelTypeList;
    
    @PostConstruct
    private void init() {
        availableMakeList = makeService.findAll();
    }

    public Make getSelectedMake()
    {
        return selectedMake;
    }

    public void setSelectedMake(Make selectedMake)
    {
        this.selectedMake = selectedMake;
    }

    public List<Make> getAvailableMakeList()
    {
        return availableMakeList;
    }

    public void setAvailableMakeList(List<Make> availableMakeList)
    {
        this.availableMakeList = availableMakeList;
    }

    public Model getSelectedModel()
    {
        return selectedModel;
    }

    public void setSelectedModel(Model selectedModel)
    {
        this.selectedModel = selectedModel;
    }

    public List<Model> getAvailableModelList()
    {
        return availableModelList;
    }

    public void setAvailableModelList(List<Model> availableModelList)
    {
        this.availableModelList = availableModelList;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public MakeService getMakeService()
    {
        return makeService;
    }

    public void setMakeService(MakeService makeService)
    {
        this.makeService = makeService;
    }

    public FuelTypeService getFueltypeService()
    {
        return fueltypeService;
    }

    public void setFueltypeService(FuelTypeService fueltypeService)
    {
        this.fueltypeService = fueltypeService;
    }

    public FuelType getSelectedFuelType()
    {
        return selectedFuelType;
    }

    public void setSelectedFuelType(FuelType selectedFuelType)
    {
        this.selectedFuelType = selectedFuelType;
    }

    public List<Make> getAvailableFuelTypeList()
    {
        return availableFuelTypeList;
    }

    public void setAvailableFuelTypeList(List<Make> availableFuelTypeList)
    {
        this.availableFuelTypeList = availableFuelTypeList;
    }


    
}
