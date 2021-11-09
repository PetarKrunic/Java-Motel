package motel.model;

import java.time.LocalDateTime;

public class Racun {
	
	private LocalDateTime datumIzdavanja;
	private double ukupnaCena;
	private Iznajmljivanje iznajmljivanje;
	private Osoba osoba;
	
	public Racun(double ukupnaCena, Iznajmljivanje iznajmljivanje, Osoba osoba) {
		super();
		this.datumIzdavanja = LocalDateTime.now();
		this.ukupnaCena = ukupnaCena;
		this.iznajmljivanje = iznajmljivanje;
		this.osoba = osoba;
	}
	
	public Racun(LocalDateTime datumIzdavanja, double ukupnaCena, Iznajmljivanje iznajmljivanje, Osoba osoba) {
		super();
		this.datumIzdavanja = datumIzdavanja;
		this.ukupnaCena = ukupnaCena;
		this.iznajmljivanje = iznajmljivanje;
		this.osoba = osoba;
	}


	public String ispis() {
		return datumIzdavanja + "|" + ukupnaCena + "|" 
				+ iznajmljivanje.getDatumPocetka() + ":" + iznajmljivanje.getSoba().getBroj() + "|"
				+ osoba.ime + ":" + osoba.prezime + ":" + osoba.getBrojLicneKarte();
	}



	public LocalDateTime getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(LocalDateTime datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public double getUkupnaCena() {
		return ukupnaCena;
	}

	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}

	public Iznajmljivanje getIznajmljivanje() {
		return iznajmljivanje;
	}

	public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
		this.iznajmljivanje = iznajmljivanje;
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}


}
