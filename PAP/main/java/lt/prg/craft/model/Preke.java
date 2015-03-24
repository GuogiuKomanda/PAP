package lt.prg.craft.model;

import lt.prg.craft.Utils;


public class Preke extends DbObjektas{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195036494654205059L;
	private String numeris;
	private String pavadinimas;
	private Double svoris;
	private String grupesPavadinimas;
	
	public String getNumeris() {
		return numeris;
	}
	public void setNumeris(String numeris) {
		this.numeris = numeris;
	}
	
	public String getPavadinimas() {
		return pavadinimas;
	}
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	public Double getSvoris() {
		return svoris;
	}
	public String getSvorisString() {
		return Utils.formatDouble3(svoris);
	}
	public void setSvoris(Double svoris) {
		this.svoris = svoris;
	}	
	public String getGrupesPavadinimas() {
		return grupesPavadinimas;
	}
	public void setGrupesPavadinimas(String grupesPavadinimas) {
		this.grupesPavadinimas = grupesPavadinimas;
	}
	
	
}
