package lt.prg.craft.bean.manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lt.prg.craft.bean.DBCacheBean;

@ManagedBean
@SessionScoped
public class UzsakymasManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4103175690419714607L;
	
	@ManagedProperty(value = "#{cache}")
	private DBCacheBean dbCacheBean;

	public DBCacheBean getDbCacheBean() {
		return dbCacheBean;
	}

	public void setDbCacheBean(DBCacheBean dbCacheBean) {
		this.dbCacheBean = dbCacheBean;
	}
	
	public List<String> getUzsakymoNumerisList(){
		return dbCacheBean.loadUzsakymoNumerisList();
	}
}
