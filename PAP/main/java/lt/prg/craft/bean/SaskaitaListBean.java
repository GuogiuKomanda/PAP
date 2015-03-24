package lt.prg.craft.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import lt.prg.craft.data.DataModel;
import lt.prg.craft.data.SaskaitaDataProvider;
import lt.prg.craft.model.Saskaita;

@ManagedBean
@SessionScoped
public class SaskaitaListBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 923087690791329529L;

	/**
	 * 
	 */
	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;
	
	private DataModel<Saskaita> dataModel;

	
	private Collection<Object> selection;

	private Saskaita selectionItem;
	
	private Saskaita edit = new Saskaita();

	private Saskaita currentItem;
	
	@PostConstruct
	private void init(){
		dataModel = new DataModel<Saskaita>(new SaskaitaDataProvider());	
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

	public Saskaita getSelectionItem() {
		return selectionItem;
	}

	public void setSelectionItem(Saskaita selectionItem) {
		this.selectionItem = selectionItem;
	}

	public void setCurrentItem(Saskaita currentItem) {
		this.currentItem = currentItem;
	}

	public Saskaita getCurrentItem() {
		return currentItem;
	}
	
	public Saskaita getEdit() {
		return edit;
	}
	
	public void actEdit(){		
		Saskaita rowData = getCurrentItem();
		edit = rowData.clone();
	}

	public void actPatvirtinti(){
		dbCacheBean.patvirtintiSaskaita(currentItem);
		reset();
		if(selection != null){
			selection.clear();
			selectionItem = null;
		}
//		currentItem = null;
	}

	public void store() {
		//store to DB
		dbCacheBean.storeSaskaita(edit);
		reset();
	}

	public void delete() {
		dbCacheBean.deleteSaskaita(edit);
		selection.clear();
		selectionItem = null;
		reset();
	}
	
	public void reset(){
		dataModel.reset();
	}

	public DataModel<Saskaita> getDataModel() {
		return dataModel;
	}
}
