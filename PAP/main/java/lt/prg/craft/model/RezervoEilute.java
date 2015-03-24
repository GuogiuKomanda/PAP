package lt.prg.craft.model;

public class RezervoEilute {
	
	private Long id;

	private String prekesNumeris;
	private Double kiekis;
	private Double kaina;
	private Double suma;
	private Subjektas tiekejas;
	private Vadybininkas pirkimoVadybininkas;
	private Vadybininkas pardavimoVadybininkas;
	private Subjektas gavejas;
	private Uzsakymas uzsakymas;
	private Saskaita saskaita;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrekesNumeris() {
		return prekesNumeris;
	}
	public void setPrekesNumeris(String prekesNumeris) {
		this.prekesNumeris = prekesNumeris;
	}
	public Double getKiekis() {
		return kiekis;
	}
	public void setKiekis(Double kiekis) {
		this.kiekis = kiekis;
	}
	public Double getKaina() {
		return kaina;
	}
	public void setKaina(Double kaina) {
		this.kaina = kaina;
	}
	public Double getSuma() {
		return suma;
	}
	public void setSuma(Double suma) {
		this.suma = suma;
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
	public Vadybininkas getPardavimoVadybininkas() {
		return pardavimoVadybininkas;
	}
	public void setPardavimoVadybininkas(Vadybininkas pardavimoVadybininkas) {
		this.pardavimoVadybininkas = pardavimoVadybininkas;
	}
	public Subjektas getGavejas() {
		return gavejas;
	}
	public void setGavejas(Subjektas gavejas) {
		this.gavejas = gavejas;
	}
	public Uzsakymas getUzsakymas() {
		return uzsakymas;
	}
	public void setUzsakymas(Uzsakymas uzsakymas) {
		this.uzsakymas = uzsakymas;
	}
	public Saskaita getSaskaita() {
		return saskaita;
	}
	public void setSaskaita(Saskaita saskaita) {
		this.saskaita = saskaita;
	}
	
	
}
