package lt.prg.craft.model;

import java.util.Date;

import lt.prg.craft.Utils;
import lt.prg.craft.db.DBConstants;

public class Uzsakymas extends DbObjektas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3593150215887977745L;

	private String pavadinimas;
	private Date uzsakymoData;
	private Date atvykimoData;
	private Double suma;
	private Double netto;
	private Subjektas tiekejas;
	private Vadybininkas pirkimoVadybininkas;

	// default
	private int statusas = 1;

	public Uzsakymas() {
	}

	public String getPavadinimas() {
		return pavadinimas;
	}

	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}

	public Date getUzsakymoData() {
		return uzsakymoData;
	}

	public void setUzsakymoData(Date uzsakymoData) {
		this.uzsakymoData = uzsakymoData;
	}

	public Date getAtvykimoData() {
		return atvykimoData;
	}

	public void setAtvykimoData(Date atvykimoData) {
		this.atvykimoData = atvykimoData;
	}

	public Double getSuma() {
		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

	public Double getNetto() {
		return netto;
	}

	public void setNetto(Double netto) {
		this.netto = netto;
	}

	public Subjektas getTiekejas() {
		return tiekejas;
	}

	public void setTiekejas(Subjektas tiekejas) {
		this.tiekejas = tiekejas;
	}

	public Vadybininkas getPirkimoVadybininkas() {
		return pirkimoVadybininkas;
	}

	public void setPirkimoVadybininkas(Vadybininkas pirkimoVadybininkas) {
		this.pirkimoVadybininkas = pirkimoVadybininkas;
	}

	public int getStatusas() {
		return statusas;
	}

	public void setStatusas(int statusas) {
		this.statusas = statusas;
	}
	
	public String getUzsakymoDataString(){
		return Utils.getDateString(uzsakymoData);
	}
	public String getAtvykimoDataString(){
		return Utils.getDateString(atvykimoData);
	}
	
	public String getPirkimoVadybininkasString() {
		return Utils.getVadybininkasString(pirkimoVadybininkas);
	}

	public String getTiekejasString() {
		return Utils.getSubjektasString(tiekejas);
	}

	public Uzsakymas clone() {
		Uzsakymas clone = new Uzsakymas();
		clone.setId(id);
		clone.setPavadinimas(pavadinimas);
		clone.setUzsakymoData(uzsakymoData);
		clone.setAtvykimoData(atvykimoData);
		clone.setSuma(suma);
		clone.setNetto(netto);
		clone.setPirkimoVadybininkas(pirkimoVadybininkas);
		clone.setTiekejas(tiekejas);
		clone.setStatusas(statusas);
		return clone;
	}
	
	public boolean isAdd() {
		return statusas == DBConstants.UZSAKYMAS_NAUJAS || id == null;
	}
	
	public boolean isDelete(){
		return statusas == DBConstants.UZSAKYMAS_NAUJAS && id != null;
	}
	
	public boolean isPrint() {
		return id != null;
	}
	
	public boolean isConfirm(){
		return statusas == DBConstants.UZSAKYMAS_NAUJAS && id != null;
	}


}
