package lt.prg.craft.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import lt.prg.craft.data.DataModel;
import lt.prg.craft.data.UzsakymasDataProvider;
import lt.prg.craft.model.Uzsakymas;

@ManagedBean
@SessionScoped
public class UzsakymasListBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2067194414588044468L;

	/**
	 * 
	 */
	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;
	
	private DataModel<Uzsakymas> dataModel = new DataModel<Uzsakymas>(new UzsakymasDataProvider());
	
	private Collection<Object> selection;

	private Uzsakymas selectionItem;
	
	private Uzsakymas edit = new Uzsakymas();

	private Uzsakymas currentItem;
	
	@PostConstruct
	private void init(){

	}

	public void setDbCacheBean(DBCacheBean dbCacheBean) {
		this.dbCacheBean = dbCacheBean;
	}

	public void selectionListener(AjaxBehaviorEvent event) {
//
		for (Object selectionKey : selection) {
			
			dataModel.setRowKey(selectionKey);
			if (dataModel.isRowAvailable()) {
				selectionItem =  dataModel.getRowData();
				break;
			}
		}
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public Uzsakymas getSelectionItem() {
		return selectionItem;
	}

	public void setSelectionItem(Uzsakymas selectionItem) {
		this.selectionItem = selectionItem;
	}

	public void setCurrentItem(Uzsakymas currentItem) {
		this.currentItem = currentItem;
	}

	public Uzsakymas getCurrentItem() {
		return currentItem;
	}
	
	public Uzsakymas getEdit() {
		return edit;
	}
	
	public void actEdit(){		
		Uzsakymas rowData = currentItem;
		edit = rowData.clone();
	}
	
	public void actPatvirtinti(){
		dbCacheBean.patvirtintiUzsakymas(currentItem);
		reset();
		if(selection != null){
			selection.clear();
			selectionItem = null;
		}
//		currentItem = null;
	}

	public void store() {
		//store to DB
		dbCacheBean.storeUzsakymas(edit);
		reset();
	}

	public void delete() {
		dbCacheBean.deleteUzsakymas(edit);
		selection.clear();
		currentItem = null;
		reset();
	}
	
	public void reset(){
		dataModel.reset();
	}

	public DataModel<Uzsakymas> getDataModel() {
		return dataModel;
	}
}
