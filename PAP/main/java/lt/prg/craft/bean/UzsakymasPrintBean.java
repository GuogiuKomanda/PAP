package lt.prg.craft.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import lt.prg.craft.model.Uzsakymas;
import lt.prg.craft.model.UzsakymoEilute;

@ManagedBean
@RequestScoped
public class UzsakymasPrintBean {

	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;
	
	@ManagedProperty(value="#{uzsakymasListBean}")
	private UzsakymasListBean uzsakymasListBean;
	
	private Uzsakymas uzsakymas;
	
	private Map<String, UzsakymoEilute> uzsakymoEilutes = new TreeMap<String, UzsakymoEilute>();
	
	private Double kiekis = 0d;
	private Double suma = 0d;
	private Double netto = 0d;
	
	@PostConstruct
	private void init(){
		uzsakymas = uzsakymasListBean.getCurrentItem();
		if(uzsakymas == null)
			return;
		List<UzsakymoEilute> eilutes = dbCacheBean.loadUzsakymoEilutes(uzsakymas.getId());
		for(UzsakymoEilute eilute : eilutes){
			
			kiekis += eilute.getKiekis();
			suma += eilute.getSuma();
			netto += eilute.getNetto();
			
			UzsakymoEilute e = uzsakymoEilutes.get(eilute.getPrekesNumeris());
			if(e == null) {
				uzsakymoEilutes.put(eilute.getPrekesNumeris(), eilute);
				continue;
			}			
			e.setSuma(e.getSuma()+eilute.getSuma());
			e.setKiekis(e.getKiekis()+eilute.getKiekis());
			e.setNetto(e.getNetto()+eilute.getNetto());
		}
	}

	public DBCacheBean getDbCacheBean() {
		return dbCacheBean;
	}

	public void setDbCacheBean(DBCacheBean dbCacheBean) {
		this.dbCacheBean = dbCacheBean;
	}

	public UzsakymasListBean getUzsakymasListBean() {
		return uzsakymasListBean;
	}

	public void setUzsakymasListBean(UzsakymasListBean uzsakymasListBean) {
		this.uzsakymasListBean = uzsakymasListBean;
	}

	public Uzsakymas getUzsakymas() {
		return uzsakymas;
	}

	public void setUzsakymas(Uzsakymas uzsakymas) {
		this.uzsakymas = uzsakymas;
	}

	public List<UzsakymoEilute> getUzsakymoEilutes() {
		return new ArrayList<UzsakymoEilute>(uzsakymoEilutes.values());
	}

	public Double getKiekis() {
		return kiekis;
	}

	public Double getSuma() {
		return suma;
	}

	public Double getNetto() {
		return netto;
	}
	
}
