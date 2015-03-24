package lt.prg.craft.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import lt.prg.craft.data.DataModel;
import lt.prg.craft.data.SaskaitosEiluteDataProvider;
import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Preke;
import lt.prg.craft.model.Saskaita;
import lt.prg.craft.model.SaskaitosEilute;
import lt.prg.craft.model.Uzsakymas;

@ManagedBean
@SessionScoped
public class SaskaitosEiluteListBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7426042690653202193L;

	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;
	
	@ManagedProperty(value="#{saskaitaListBean}")
	private SaskaitaListBean saskaitaListBean;
	
	private Long currentSaskaitosId = null;
	private DataModel<SaskaitosEilute> dataModel;
	
	private Collection<Object> selection;
	
	private SaskaitosEilute selectionItem;
	
	private SaskaitosEilute edit;
	
	private SaskaitosEilute currentItem ;
	
	private String prekesNumerisFilter;
	
	private String grupeFilter;
	
	private String uzsakymoNumerisFilter;
	
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

	public void uzsakymoNumerisChangeListener(AjaxBehaviorEvent event) {
		String uzsakymoNumeris = edit.getUzsakymoNumeris();
		Uzsakymas uzsakymas = dbCacheBean.loadUzsakymas(uzsakymoNumeris);
		edit.setUzsakymoId(uzsakymas.getId());
	}
	
	public void prekesNumerisChangeListener(AjaxBehaviorEvent event) {
		String prekesNumeris = edit.getPrekesNumeris();
		Preke preke = dbCacheBean.loadPreke(prekesNumeris);
		edit.setPreke(preke);
		try {
			Double kaina = DBUtils.getPrekesKaina(edit.getUzsakymoId(), preke.getId());
			edit.setKaina(kaina);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public SaskaitosEilute getSelectionItem() {
		return selectionItem;
	}

	public void setSelectionItem(SaskaitosEilute selectionItem) {
		this.selectionItem = selectionItem;
	}
	
	public SaskaitosEilute getCurrentItem() {
		return currentItem;
	}

	public void setCurrentItem(SaskaitosEilute currentItem) {
		this.currentItem = currentItem;
	}
	
	public SaskaitosEilute getEdit() {
		return edit;
	}
	
	public void actEdit(){		
		SaskaitosEilute rowData = getCurrentItem();
		edit = rowData.clone();
	}

	public void store() {
		//store to DB
		dbCacheBean.storeSaskaitosEilute(edit);
		reset();
	}

	public void delete() {
		dbCacheBean.deleteSaskaitosEilute(edit);
		selection.clear();
		selectionItem = null;
		reset();
	}
	
	public void reset(){
		saskaitaListBean.reset();
		dataModel.reset();
	}

	public SaskaitaListBean getSaskaitaListBean() {
		return saskaitaListBean;
	}

	public void setSaskaitaListBean(SaskaitaListBean saskaitaListBean) {
		this.saskaitaListBean = saskaitaListBean;
	}

	
	public DataModel<SaskaitosEilute> getDataModel() {
		Saskaita saskaita = saskaitaListBean.getSelectionItem();
		Long saskaitosId = saskaita == null? null : saskaita.getId();
		if( !(saskaitosId == null && currentSaskaitosId == null || (saskaitosId != null && saskaitosId.equals(currentSaskaitosId)))){
			currentSaskaitosId = saskaitosId;
			//init data model
			dataModel = new DataModel<SaskaitosEilute>(new SaskaitosEiluteDataProvider(saskaitosId));
		}
		
		return dataModel;
	}
	
	public String getUzsakymoNumerisFilter() {
		return uzsakymoNumerisFilter;
	}

	public void setUzsakymoNumerisFilter(String uzsakymoNumerisFilter) {
		this.uzsakymoNumerisFilter = uzsakymoNumerisFilter;
	}

	public String getPrekesNumerisFilter() {
		return prekesNumerisFilter;
	}

	public void setPrekesNumerisFilter(String prekesNumerisFilter) {
		this.prekesNumerisFilter = prekesNumerisFilter;
	}

	public String getGrupeFilter() {
		return grupeFilter;
	}

	public void setGrupeFilter(String grupeFilter) {
		this.grupeFilter = grupeFilter;
	}
}
