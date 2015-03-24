package lt.prg.craft.model;

import java.util.Date;

import lt.prg.craft.Utils;

public class Saskaita extends DbObjektas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8357779962939938708L;

	private String pavadinimas;
	private Date data;
	private Double suma;
	private Double kiekis;
	private Subjektas tiekejas;
	private Vadybininkas pardavimoVadybininkas;
	
	private Integer statusas;

//	private List<SaskaitosEilute> eilutes = new ArrayList<SaskaitosEilute>();

//	public List<SaskaitosEilute> getEilutes() {
//		return eilutes;
//	}

	public String getPavadinimas() {
		return pavadinimas;
	}

	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getSuma() {
		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

	public Double getKiekis() {
		return kiekis;
	}

	public void setKiekis(Double kiekis) {
		this.kiekis = kiekis;
	}

	public Subjektas getTiekejas() {
		return tiekejas;
	}

	public void setTiekejas(Subjektas tiekejas) {
		this.tiekejas = tiekejas;
	}

	public Vadybininkas getPardavimoVadybininkas() {
		return pardavimoVadybininkas;
	}

	public void setPardavimoVadybininkas(Vadybininkas pardavimoVadybininkas) {
		this.pardavimoVadybininkas = pardavimoVadybininkas;
	}

	public Saskaita clone() {
		Saskaita clone = new Saskaita();
		clone.setId(id);
		clone.setPardavimoVadybininkas(pardavimoVadybininkas);
		clone.setPavadinimas(pavadinimas);
		clone.setTiekejas(tiekejas);
//		clone.getEilutes().addAll(eilutes);
		return clone;
	}

	public String getDataString() {
		return Utils.getDateString(data);
	}

	public String getTiekejasString() {
		return Utils.getSubjektasString(tiekejas);
	}

	public String getPardavimoVadybininkasString() {
		return Utils.getVadybininkasString(pardavimoVadybininkas);
	}

	public Integer getStatusas() {
		return statusas;
	}

	public void setStatusas(Integer statusas) {
		this.statusas = statusas;
	}
}
