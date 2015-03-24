package lt.prg.craft.model;

public class Vadybininkas extends DbObjektas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3002019955875713372L;
	
	private String vardas;
	private String pavarde;
	private Integer tipas;

	public String getVardas() {
		return vardas;
	}

	public void setVardas(String vardas) {
		this.vardas = vardas;
	}

	public String getPavarde() {
		return pavarde;
	}

	public void setPavarde(String pavarde) {
		this.pavarde = pavarde;
	}

	public Integer getTipas() {
		return tipas;
	}

	public void setTipas(Integer tipas) {
		this.tipas = tipas;
	}

}
