package lt.prg.craft.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lt.prg.craft.db.DBUtils;
import lt.prg.craft.model.Preke;
import lt.prg.craft.model.Saskaita;
import lt.prg.craft.model.SaskaitosEilute;
import lt.prg.craft.model.Subjektas;
import lt.prg.craft.model.Uzsakymas;
import lt.prg.craft.model.UzsakymoEilute;
import lt.prg.craft.model.Vadybininkas;

@ManagedBean(name="cache", eager=true)
@ApplicationScoped
public class DBCacheBean implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -3016186698986845567L;

//	private Map<Long, Saskaita> cachedSaskaita = new HashMap<Long, Saskaita>();
//	private Map<Long, List<SaskaitosEilute>> cachedSaskaitosEilutes= new HashMap<Long, List<SaskaitosEilute>>();

	public List<Uzsakymas> loadUzsakymai() {
		try {
			List<Uzsakymas> list = DBUtils.loadUzsakymai();
//			cachedSaskaita = new HashMap<Long, Saskaita>();
//			for (Saskaita saskaita : list) {
//				cachedSaskaita.put(saskaita.getId(), saskaita);
//			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Uzsakymas>();
	}
	
	public List<Saskaita> loadSaskaitos() {
		try {
			List<Saskaita> list = DBUtils.loadSaskaitos();
//			cachedSaskaita = new HashMap<Long, Saskaita>();
//			for (Saskaita saskaita : list) {
//				cachedSaskaita.put(saskaita.getId(), saskaita);
//			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Saskaita>();
	}
	
	
	public List<UzsakymoEilute> loadUzsakymoEilutes(Long uzsakymoId){
		try {
			List<UzsakymoEilute> list = DBUtils.loadUzsakymoEilutes(uzsakymoId);
//			cachedSaskaitosEilutes.put(uzsakymoId, list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<UzsakymoEilute>();
	}
	
	public List<SaskaitosEilute> loadSaskaitosEilutes(Long saskaitosId){
		try {
			List<SaskaitosEilute> list = DBUtils.loadSaskaitosEilutes(saskaitosId);
//			cachedSaskaitosEilutes.put(saskaitosId, list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<SaskaitosEilute>();
	}
	
	public List<SaskaitosEilute> getSaskaitosEilutes(Long saskaitosId) {
//		List<SaskaitosEilute> list = cachedSaskaitosEilutes.get(saskaitosId);
//		if (list == null) {
			try {
				List<SaskaitosEilute> list = DBUtils.loadSaskaitosEilutes(saskaitosId);
//				cachedSaskaitosEilutes.put(saskaitosId, list);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return new ArrayList<SaskaitosEilute>();
	}
	
	public Preke loadPreke(String prekesNumeris){
		Preke preke = null;
		try {
			preke = DBUtils.getPreke( prekesNumeris);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preke;
	}
	
	public Uzsakymas loadUzsakymas(String uzsakymoNumeris){
		Uzsakymas uzsakymas = null;
		try {
			uzsakymas = DBUtils.loadUzsakymas( uzsakymoNumeris);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uzsakymas;
	}
	
	public List<String> loadPrekesNumerisList(String uzsakymoNumeris){
		List<String> prekesNumerisList = new ArrayList<String>();
		try {
			prekesNumerisList =  DBUtils.getPrekesNumerisList(uzsakymoNumeris);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prekesNumerisList;
	}
	public List<String> loadPrekesNumerisList(){
		List<String> prekesNumerisList = new ArrayList<String>();
		try {
			prekesNumerisList =  DBUtils.getPrekesNumerisList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prekesNumerisList;
	}
	
	public List<String> loadUzsakymoNumerisList(){
		List<String> uzsakymoNumerisList = new ArrayList<String>();
		try {
			uzsakymoNumerisList =  DBUtils.getUzsakymoNumerisList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uzsakymoNumerisList;
	}
	
	public List<Vadybininkas> loadVadybininkai(Integer tipas){
		List<Vadybininkas> vadybininkai = new ArrayList<Vadybininkas>();
		try {
			vadybininkai =  DBUtils.getVadybininkasListByTipas(tipas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vadybininkai;
	}
	
	public List<Subjektas> loadSubjektai(Integer tipas){
		List<Subjektas> subjektai = new ArrayList<Subjektas>();
		try {
			subjektai =  DBUtils.getSubjektasListByTipas(tipas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return subjektai;
	}
	
	public void patvirtintiUzsakymas(Uzsakymas uzsakymas){
		try {
			DBUtils.patvirtintiUzsakymas(uzsakymas.getId());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeUzsakymas(Uzsakymas uzsakymas){
		try {
			if(uzsakymas.getId() == null){
				DBUtils.insertUzsakymas(uzsakymas);
			} else {
				DBUtils.updateUzsakymas(uzsakymas);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeUzsakymoEilute(UzsakymoEilute uzsakymoEilute){
		try {
			if(uzsakymoEilute.getId() == null){
				DBUtils.insertUzsakymoEilute(uzsakymoEilute);
			} else {
				DBUtils.updateUzsakymoEilute(uzsakymoEilute);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteUzsakymas(Uzsakymas uzsakymas){
		try {
			DBUtils.deleteUzsakymas(uzsakymas.getId());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteUzsakymoEilute(UzsakymoEilute uzsakymoEilute){
		try {
			DBUtils.deleteUsakymoEilute(uzsakymoEilute.getId());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/****/
	public void patvirtintiSaskaita(Saskaita saskaita){
		try {
			DBUtils.patvirtintiSaskaita(saskaita.getId());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeSaskaita(Saskaita saskaita){
		try {
			if(saskaita.getId() == null){
				DBUtils.insertSaskaita(saskaita);
			} else {
				DBUtils.updateSaskaita(saskaita);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void storeSaskaitosEilute(SaskaitosEilute saskaitosEilute){
		try {
			if(saskaitosEilute.getId() == null){
				DBUtils.insertSaskaitosEilute(saskaitosEilute);
			} else {
				DBUtils.updateSaskaitosEilute(saskaitosEilute);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteSaskaita(Saskaita saskaita){
		try {
			DBUtils.deleteSaskaita(saskaita.getId());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteSaskaitosEilute(SaskaitosEilute saskaitosEilute){
		try {
			DBUtils.deleteUsakymoEilute(saskaitosEilute.getId());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
