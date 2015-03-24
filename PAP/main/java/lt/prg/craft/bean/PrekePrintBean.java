package lt.prg.craft.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Preke;

@ManagedBean
@RequestScoped
public class PrekePrintBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8793516833210176295L;

	@ManagedProperty(value = "#{prekeListBean}")
	private PrekeListBean prekeListBean;
	
	private List<Preke> prekes;
	
	@PostConstruct
	private void init(){
		prekes = new ArrayList<Preke>();
		
		String numerisFilter = prekeListBean.getNumerisFilter();
		String pavadinimasFilter = prekeListBean.getPavadinimasFilter();
		String grupeFilter = prekeListBean.getGrupeFilter();
		
		if(numerisFilter == null)
			numerisFilter = "";
		if(pavadinimasFilter == null)
			pavadinimasFilter = "";
		if(grupeFilter == null)
			grupeFilter = "";
		
		numerisFilter = numerisFilter.toLowerCase();
		pavadinimasFilter = pavadinimasFilter.toLowerCase();
		grupeFilter = grupeFilter.toLowerCase();
		
		for(Preke preke : DBUtils.getPrekes()){
			if(check(preke.getNumeris(), numerisFilter) && 
			   check(preke.getPavadinimas(),pavadinimasFilter) &&
			   check(preke.getGrupesPavadinimas(),grupeFilter)){
				prekes.add(preke);
			}
		}
	}

	public PrekeListBean getPrekeListBean() {
		return prekeListBean;
	}

	public void setPrekeListBean(PrekeListBean prekeListBean) {
		this.prekeListBean = prekeListBean;
	}

	public List<Preke> getPrekes() {
		return prekes;
	}
	
	private static boolean check(String value, String filter){
		if(value == null)
			value = "";
		value = value.toLowerCase();
		return value.contains(filter);
	}
	
	
}
