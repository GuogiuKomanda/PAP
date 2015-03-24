package lt.prg.craft.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import lt.prg.craft.data.DataModel;
import lt.prg.craft.data.UzsakymoEiluteDataProvider;
import lt.prg.craft.model.Preke;
import lt.prg.craft.model.Uzsakymas;
import lt.prg.craft.model.UzsakymoEilute;

@ManagedBean
@ViewScoped
public class UzsakymoEiluteListBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350084241728915743L;

	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;
	
	@ManagedProperty(value="#{uzsakymasListBean}")
	private UzsakymasListBean uzsakymasListBean;
	
	private Long currentUzsakymoId = null;
	private DataModel<UzsakymoEilute> dataModel;
	
	private Collection<Object> selection;
	
	private UzsakymoEilute selectionItem;
	
	private UzsakymoEilute edit;
	
	private UzsakymoEilute currentItem ;
	
	
//	private String uzsakymasNumerisFilter;
	
	public void setDbCacheBean(DBCacheBean dbCacheBean) {
		this.dbCacheBean = dbCacheBean;
	}
	
	public void selectionListener(AjaxBehaviorEvent event) {
		for (Object selectionKey : selection) {
			
			dataModel.setRowKey(selectionKey);
			if (dataModel.isRowAvailable()) {
				selectionItem =  dataModel.getRowData();
				break;
			}
		}
	}
	
	public void prekesNumerisChangeListener(AjaxBehaviorEvent event) {
		String prekesNumeris = edit.getPrekesNumeris();
		Preke preke = dbCacheBean.loadPreke(prekesNumeris);
		edit.setPreke(preke);
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public UzsakymoEilute getSelectionItem() {
		return selectionItem;
	}

	public void setSelectionItem(UzsakymoEilute selectionItem) {
		this.selectionItem = selectionItem;
	}
	
	public UzsakymoEilute getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(UzsakymoEilute currentItem) {
		this.currentItem = currentItem;
	}
	
	public UzsakymoEilute getEdit() {
		return edit;
	}
	
	public void actEdit(){		
		UzsakymoEilute rowData = getCurrentItem();
		edit = rowData.clone();
	}

	public void store() {
		//store to DB
		dbCacheBean.storeUzsakymoEilute(edit);
		reset();
	}

	public void delete() {
		dbCacheBean.deleteUzsakymoEilute(edit);
		selection.clear();
		selectionItem = null;
		reset();
	}
	
	public void reset(){
		uzsakymasListBean.reset();
		dataModel.reset();
	}

	public UzsakymasListBean getUzsakymasListBean() {
		return uzsakymasListBean;
	}

	public void setUzsakymasListBean(UzsakymasListBean uzsakymasListBean) {
		this.uzsakymasListBean = uzsakymasListBean;
	}
	
	public DataModel<UzsakymoEilute> getDataModel() {
		Uzsakymas uzsakymas = uzsakymasListBean.getSelectionItem();
		Long uzsakymoId = uzsakymas == null? null : uzsakymas.getId();
		if( !(uzsakymoId == null && currentUzsakymoId == null || (uzsakymoId != null && uzsakymoId.equals(currentUzsakymoId)))){
			currentUzsakymoId = uzsakymoId;
			dataModel = new DataModel<UzsakymoEilute>(new UzsakymoEiluteDataProvider(uzsakymoId));
		}
		
		return dataModel;
	}
	
//	public String getUzsakymoNumerisFilter() {
//		return uzsakymasNumerisFilter;
//	}
//
//	public void setUzsakymoNumerisFilter(String uzsakymoNumerisFilter) {
//		this.uzsakymoNumerisFilter = uzsakymoNumerisFilter;
//	}
}
