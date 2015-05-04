package lt.pap.model.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import lombok.core.configuration.ConfigurationParser.Collector;
import lt.pap.model.FuelType;
import lt.pap.model.Make;
import lt.pap.model.Model;
import lt.pap.model.ModelGroup;

public class Functions {

	private static 	Function<Make, SelectItem> makeToSelectItem = new Function<Make, SelectItem>() {

		public SelectItem apply(Make make) {
			SelectItem item = new SelectItem();
			item.setLabel(make.getName());
			item.setValue(make.getId());
			return item;
		}
	};
	
//	private static 	Function<ModelGroup, SelectItem> modelGroupToSelectItem = new Function<ModelGroup, SelectItem>() {
//
//		public SelectItem apply(ModelGroup modelGroup) {
//			SelectItemGroup group = new SelectItemGroup(modelGroup.getName());
//			SelectItem[] items =  modelGroup.getModelList().stream().map(modelToSelectItem).toArray(SelectItem[]::new);
//			group.setSelectItems(items);
//			return group;
//		}
//	};
	/*
	private static BiFunction<ModelGroup, List<Model>, SelectItem> modelGroupToSelectItem = new BiFunction<ModelGroup, List<Model>, SelectItem>() {

		@Override
		public SelectItem apply(ModelGroup modelGroup, List<Model> modelList) {
			SelectItemGroup group = new SelectItemGroup(modelGroup.getName());
			SelectItem[] items =  modelList.stream().map(modelToSelectItem).toArray(SelectItem[]::new);
			group.setSelectItems(items);
			return group;
		}
		
	};
	*/
	private static 	Function<Model, SelectItem> modelToSelectItem = new Function<Model, SelectItem>() {

		public SelectItem apply(Model model) {
			SelectItem item = new SelectItem();
			item.setLabel(model.getName());
			item.setValue(model.getId());
			return item;
		}
	};
	
	private static 	Function<FuelType, SelectItem> fuelToSelectItem = new Function<FuelType, SelectItem>() {

		public SelectItem apply(FuelType ft) {
			SelectItem item = new SelectItem();
			item.setLabel(ft.getName());
			item.setValue(ft.getId());
			return item;
		}
	};
	
	public static List<SelectItem> makeToSelectItems(List<Make> makeList) {
		return  makeList.stream().map(makeToSelectItem).collect(Collectors.toList());
	}
	
	public static List<SelectItem> modelToSelectItems(List<Model> modelList) {
		 Map<ModelGroup, List<Model>> groupByModelGroup = modelList.stream().collect( Collectors.groupingBy(Model::getModelGroup));
		 return groupByModelGroup.entrySet().stream().map( e-> createItemGroup( e.getKey(),e.getValue()) ).collect(Collectors.toList());
	}
	
	public static List<SelectItem> fuelTypeToSelectItems(List<FuelType> fuelTypeList) {
		return  fuelTypeList.stream().map(fuelToSelectItem).collect(Collectors.toList());
	}
	
	private static SelectItem createItemGroup(ModelGroup modelGroup, List<Model> modelList) {
		SelectItemGroup group = new SelectItemGroup(modelGroup.getName());
		SelectItem[] items =  modelList.stream().map(modelToSelectItem).toArray(SelectItem[]::new);
		group.setSelectItems(items);
		return group;
	}
	
    public static List<Long> modelListToId(List<Model> modelList) {
      return modelList.stream().map(i -> i.getId()).collect(Collectors.toList());
    }
    
    public static List<Long> fuelTypeListToId(List<FuelType> fuelList) {
      return fuelList.stream().map(i -> i.getId()).collect(Collectors.toList());
    }
}
