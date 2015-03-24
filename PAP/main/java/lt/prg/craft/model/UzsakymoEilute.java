package lt.prg.craft.model;

import lt.prg.craft.Utils;
import lt.prg.craft.db.DBConstants;


public class UzsakymoEilute extends DbObjektas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1985629534261231035L;
	
	private Long uzsakymoId;
	private String uzsakymoNumeris;
	private String prekesNumeris;

	private Double kaina;

	private Double kiekis;

	private Double suma;
	private Double netto;

	private Preke preke;

	private Vadybininkas pardavimoVadybininkas;
	private Subjektas klientas;
	
	private Integer statusas;

	
	public Long getUzsakymoId() {
		return uzsakymoId;
	}

	public void setUzsakymoId(Long uzsakymoId) {
		this.uzsakymoId = uzsakymoId;
	}

	public String getPrekesNumeris() {
		return prekesNumeris;
	}

	public void setPrekesNumeris(String prekesNumeris) {
		this.prekesNumeris = prekesNumeris;
	}
	
	public String getUzsakymoNumeris() {
		return uzsakymoNumeris;
	}

	public void setUzsakymoNumeris(String uzsakymoNumeris) {
		this.uzsakymoNumeris = uzsakymoNumeris;
	}
	public Double getKiekis() {
		return kiekis;
	}

	public void setKiekis(Double kiekis) {
		this.kiekis = kiekis;
		calculateFields();
	}

	public Double getKaina() {
		return kaina;
	}

	public void setKaina(Double kaina) {
		this.kaina = kaina;
		calculateFields();
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

	public Vadybininkas getPardavimoVadybininkas() {
		return pardavimoVadybininkas;
	}

	public void setPardavimoVadybininkas(Vadybininkas pardavimoVadybininkas) {
		this.pardavimoVadybininkas = pardavimoVadybininkas;
	}

	public Subjektas getKlientas() {
		return klientas;
	}

	public void setKlientas(Subjektas klientas) {
		this.klientas = klientas;
	}

	public Integer getStatusas() {
		return statusas;
	}

	public void setStatusas(Integer statusas) {
		this.statusas = statusas;
	}

	public Preke getPreke() {
		return preke;
	}

	public void setPreke(Preke preke) {
		this.preke = preke;
		calculateFields();
	}
	public String getNettoString() {
		return Utils.formatDouble3(netto);
	}

	public String getSumaString() {
		return Utils.formatDouble2(suma);
	}
	
	public String getPrekesPavadinimas() {
		if (preke == null)
			return "";
		return preke.getPavadinimas();
	}

	public String getPrekesSvoris() {
		if (preke == null)
			return "";
		return Utils.formatDouble3(preke.getSvoris());
	}

	public String getPrekesGrupesPavadinimas() {
		if (preke == null)
			return "";
		return preke.getGrupesPavadinimas();
	}

	private void calculateFields() {
		if (preke != null && kiekis != null) {

			netto = preke.getSvoris() * kiekis;
		}

		if (kaina != null && kiekis != null) {
			suma = kaina * kiekis;
		}
	}
	
	public String getPardavimoVadybininkasString(){
		return Utils.getVadybininkasString(pardavimoVadybininkas);
	}
	
	public String getKlientasString(){
		return Utils.getSubjektasString(klientas);
	}

	public UzsakymoEilute clone() {
		UzsakymoEilute clone = new UzsakymoEilute();
		clone.setId(id);
		clone.setKiekis(kiekis);
		clone.setKaina(kaina);
		clone.setPreke(preke);
		clone.setKlientas(klientas);
		clone.setPardavimoVadybininkas(pardavimoVadybininkas);
		clone.setPrekesNumeris(prekesNumeris);
		clone.setUzsakymoNumeris(uzsakymoNumeris);
		clone.setStatusas(statusas);
		clone.setUzsakymoId(uzsakymoId);
		return clone;
	}
	
	public boolean isNauja(){
		return statusas == DBConstants.UZSAKYMO_EILUTE_NAUJA;
	}

}