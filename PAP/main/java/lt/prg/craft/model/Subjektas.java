package lt.prg.craft.model;


public class Subjektas extends DbObjektas{

	private static final long serialVersionUID = 7372691397439139184L;
	
	private String pavadinimas;
	private Integer tipas;
	
	public String getPavadinimas() {
		return pavadinimas;
	}
	public void setPavadinimas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	public Integer getTipas() {
		return tipas;
	}
	public void setTipas(Integer tipas) {
		this.tipas = tipas;
	}
	
}
