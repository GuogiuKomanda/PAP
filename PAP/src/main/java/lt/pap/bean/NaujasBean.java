package lt.pap.bean;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import lt.pap.model.Engine;
import lt.pap.model.FuelType;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.model.WPart;
import lt.pap.model.utils.Functions;
import lt.pap.service.FuelTypeService;
import lt.pap.service.MakeService;
import lt.pap.service.ModelGroupService;
import lt.pap.service.ModelService;
import lt.pap.service.WPartService;

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
	private ModelGroupService modelGroupService;

	@Autowired
	private FuelTypeService fueltypeService;

	@Autowired
	private WPartService wpartService;
	// make
	private String selectedMake;

	private List<SelectItem> availableMakeList;

	// model
	private List<SelectItem> selectedModelList;

	private List<SelectItem> availableModelList;

	// fuel
	private List<SelectItem> selectedFuelTypeList;

	private List<SelectItem> availableFuelTypeList;
	
	private List<WPart> wpartList;

	@PostConstruct
	private void init() {
		availableMakeList = Functions.makeToSelectItems(makeService.findAll());
		availableModelList = Functions.modelToSelectItems(modelService.findAll());
		availableFuelTypeList = Functions.fuelTypeToSelectItems(fueltypeService.findAll());

	}

	public void updateModelList() {
		if (selectedMake != null) {
			Make mk =  makeService.findOne(Long.parseLong(selectedMake));
			availableModelList = Functions.modelToSelectItems(modelService.findByModelGroupMakeId(mk.getId()));
		} else {
		  availableModelList = Functions.modelToSelectItems(modelService.findAll());
		}
	}

	public void updateFuelList() {
//		if (selectedModelList != null) {
////			Make mk =  makeService.findOne(Long.parseLong(selectedMake));
////			availableModelList = Functions.modelToSelectItems(modelService.findByModelGroupMakeId(mk.getId()));
//			List<Long> modelIdList = selectedModelList.stream().map(m -> ((Model)m.getValue()).getId()).collect(Collectors.toList());
////			List<>
////			availableFuelTypeList
//		} else {
//			availableFuelTypeList = Functions.fuelTypeToSelectItems(fueltypeService.findAll());
//		}
	}
	
	public void doSearch(){
		
//		List<Engine> engineList = 
//		wpartList = wpartService.findByFilters(selectedMake);
		wpartList = wpartService.findAll();
	}



	public String getSelectedMake() {
		return selectedMake;
	}

	public void setSelectedMake(String selectedMake) {
		this.selectedMake = selectedMake;
	}

	public List<SelectItem> getAvailableMakeList() {
		return availableMakeList;
	}

	public void setAvailableMakeList(List<SelectItem> availableMakeList) {
		this.availableMakeList = availableMakeList;
	}

	public List<SelectItem> getSelectedModelList() {
		return selectedModelList;
	}

	public void setSelectedModelList(List<SelectItem> selectedModelList) {
		this.selectedModelList = selectedModelList;
	}

	public List<SelectItem> getAvailableModelList() {
		return availableModelList;
	}

	public void setAvailableModelList(List<SelectItem> availableModelList) {
		this.availableModelList = availableModelList;
	}

	public List<SelectItem> getSelectedFuelTypeList() {
		return selectedFuelTypeList;
	}

	public void setSelectedFuelTypeList(List<SelectItem> selectedFuelTypeList) {
		this.selectedFuelTypeList = selectedFuelTypeList;
	}

	public List<SelectItem> getAvailableFuelTypeList() {
		return availableFuelTypeList;
	}

	public void setAvailableFuelTypeList(List<SelectItem> availableFuelTypeList) {
		this.availableFuelTypeList = availableFuelTypeList;
	}

	public List<WPart> getWpartList() {
		return wpartList;
	}
	
}
