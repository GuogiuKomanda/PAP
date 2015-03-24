package lt.prg.craft.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lt.prg.craft.data.DataModel;
import lt.prg.craft.data.PrekeDataProvider;
import lt.prg.craft.model.Preke;

@ManagedBean
@SessionScoped
public class PrekeListBean implements Serializable{
	
	private String pavadinimasFilter;
	
	private String numerisFilter;
	
	private String grupeFilter;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5856677083128082212L;
	
	private DataModel<Preke> dataModel = new DataModel<Preke>(new PrekeDataProvider());

	public void reset(){
		dataModel.reset();
	}

	public DataModel<Preke> getDataModel() {
		return dataModel;
	}

	public String getPavadinimasFilter() {
		return pavadinimasFilter;
	}

	public void setPavadinimasFilter(String pavadinimasFilter) {
		this.pavadinimasFilter = pavadinimasFilter;
	}

	public String getNumerisFilter() {
		return numerisFilter;
	}

	public void setNumerisFilter(String numerisFilter) {
		this.numerisFilter = numerisFilter;
	}

	public String getGrupeFilter() {
		return grupeFilter;
	}

	public void setGrupeFilter(String grupeFilter) {
		this.grupeFilter = grupeFilter;
	}
	
	
}
