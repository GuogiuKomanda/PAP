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
import lt.prg.craft.model.Vadybininkas;
@ManagedBean
@SessionScoped
public class VadybininkasManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4778224365433478833L;
	
	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;

	public DBCacheBean getDbCacheBean() {
		return dbCacheBean;
	}

	public void setDbCacheBean(DBCacheBean dbCacheBean) {
		this.dbCacheBean = dbCacheBean;
	}
	
	public Map<String, Vadybininkas> getPirkimoVadybininkasList(){
		return getVadybininkasList(DBConstants.VADYBININKAS_PIRKIMO);
	}
	
	public Map<String, Vadybininkas> getPardavimoVadybininkasList(){
		return getVadybininkasList(DBConstants.VADYBININKAS_PARDAVIMO);
	}
	
	private Map<String, Vadybininkas> getVadybininkasList(Integer tipas){
		Map<String, Vadybininkas> map = new TreeMap<String, Vadybininkas>();
		for(Vadybininkas vadybininkas : dbCacheBean.loadVadybininkai(tipas)){
			map.put(Utils.getVadybininkasString(vadybininkas), vadybininkas);
		}
		return map;
	}
}
