package lt.prg.craft.bean.manager;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lt.prg.craft.Utils;
import lt.prg.craft.bean.DBCacheBean;
import lt.prg.craft.db.DBConstants;
import lt.prg.craft.model.Subjektas;

@ManagedBean
@SessionScoped
public class SubjektasManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6451927177377321646L;
	
	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;

	public DBCacheBean getDbCacheBean() {
		return dbCacheBean;
	}

	public void setDbCacheBean(DBCacheBean dbCacheBean) {
		this.dbCacheBean = dbCacheBean;
	}
	
	public Map<String, Subjektas> getKlientasList(){
		return getSubjektasList(DBConstants.SUBJEKTAS_KLIENTAS);
	}
	
	public Map<String, Subjektas> getTiekejasList(){
		return getSubjektasList(DBConstants.SUBJEKTAS_TIEKEJAS);
	}
	
	private Map<String, Subjektas> getSubjektasList(Integer tipas){
		Map<String, Subjektas> map = new TreeMap<String, Subjektas>();
		for(Subjektas subjektas : dbCacheBean.loadSubjektai(tipas)){
			map.put(Utils.getSubjektasString(subjektas), subjektas);
		}
		return map;
	}
}
