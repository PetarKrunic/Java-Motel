package motel.model;

import java.time.LocalDateTime;

public class StavkaCenovnika {

	private LocalDateTime datumKreiranja;
	private double dnevniBoravak;
	private double nocenje;
	private double vikendPoskupljenje;
	private TipSobe tipSobe;
	private boolean obrisan;
	
	public StavkaCenovnika(double dnevniBoravak, double nocenje, double vikendPoskupljenje, TipSobe tipSobe) {
		super();
		this.datumKreiranja = LocalDateTime.now();
		this.dnevniBoravak = dnevniBoravak;
		this.nocenje = nocenje;
		this.vikendPoskupljenje = vikendPoskupljenje;
		this.tipSobe = tipSobe;
		this.obrisan = false;
	}
	

	public StavkaCenovnika(LocalDateTime datumKreiranja, double dnevniBoravak, double nocenje, double vikendPoskupljenje,
			TipSobe tipSobe, boolean obrisan) {
		super();
		this.datumKreiranja = datumKreiranja;
		this.dnevniBoravak = dnevniBoravak;
		this.nocenje = nocenje;
		this.vikendPoskupljenje = vikendPoskupljenje;
		this.tipSobe = tipSobe;
		this.obrisan = obrisan;
	}
	
	public String ispis() {
		return obrisan +"|"+ datumKreiranja + "|" + dnevniBoravak + "|" + nocenje + "|" + vikendPoskupljenje + "|" 
					+ tipSobe.getNaziv() +":"+tipSobe.getBrojKreveta();
	}


	public TipSobe getTipSobe() {
		return tipSobe;
	}


	public void setTipSobe(TipSobe tipSobe) {
		this.tipSobe = tipSobe;
	}


	public LocalDateTime getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(LocalDateTime datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public double getDnevniBoravak() {
		return dnevniBoravak;
	}

	public void setDnevniBoravak(double dnevniBoravak) {
		this.dnevniBoravak = dnevniBoravak;
	}

	public double getNocenje() {
		return nocenje;
	}

	public void setNocenje(double nocenje) {
		this.nocenje = nocenje;
	}

	public double getVikendPoskupljenje() {
		return vikendPoskupljenje;
	}

	public void setVikendPoskupljenje(double vikendPoskupljenje) {
		this.vikendPoskupljenje = vikendPoskupljenje;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
}
