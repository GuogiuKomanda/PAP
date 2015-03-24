package lt.prg.craft.model;

import lt.prg.craft.Utils;

public class SaskaitosEilute extends DbObjektas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7427511482953099671L;
	
	
	private Long saskaitosId;
	private Long uzsakymoId;
	
	private String uzsakymoNumeris;
	private String prekesNumeris;

	private Double kaina;
	private Double kiekis;

	private Double suma;
	private Double netto;

	private Preke preke;
	
	private Integer statusas;

	public Long getSaskaitosId() {
		return saskaitosId;
	}

	public void setSaskaitosId(Long saskaitosId) {
		this.saskaitosId = saskaitosId;
	}

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

	public Double getKaina() {
		return kaina;
	}

	public void setKaina(Double kaina) {
		this.kaina = kaina;
		calculateFields();
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

	public SaskaitosEilute clone() {
		SaskaitosEilute clone = new SaskaitosEilute();
		clone.setId(id);
		clone.setSaskaitosId(saskaitosId);
		clone.setUzsakymoId(uzsakymoId);
		clone.setKaina(kaina);
		clone.setKiekis(kiekis);
		clone.setPrekesNumeris(prekesNumeris);
		clone.setUzsakymoNumeris(uzsakymoNumeris);
		clone.setPreke(preke);
		
		return clone;
	}

	public Integer getStatusas() {
		return statusas;
	}

	public void setStatusas(Integer statusas) {
		this.statusas = statusas;
	}
}
