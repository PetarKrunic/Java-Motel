package motel.model;

import java.time.LocalDateTime;
import java.util.List;

public class Iznajmljivanje {

	private List<Osoba> gosti;
	private Soba soba;
	private LocalDateTime datumPocetka;
	private LocalDateTime datumZavrsetka;
	private boolean obrisan;

	public Iznajmljivanje(List<Osoba> gosti, Soba soba, LocalDateTime datumPocetka, LocalDateTime datumZavrsetka) {
		super();
		this.gosti = gosti;
		this.soba = soba;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.obrisan = false;
	}
	
	
	
	public Iznajmljivanje(List<Osoba> gosti, Soba soba, LocalDateTime datumPocetka, LocalDateTime datumZavrsetka, boolean obrisan) {
		super();
		this.gosti = gosti;
		this.soba = soba;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.obrisan = obrisan;
	}
	
	public String ispis() {
		String string = obrisan + "|" + datumPocetka + "|" + datumZavrsetka + "|" + soba.getBroj() + "|";
		for(Osoba gost : gosti) {
			string += gost.getIme() + ":" + gost.getPrezime() + ":" + gost.getBrojLicneKarte() + ";";
		}
		string = string.substring(0, string.length()-1);
		return string;
	}



	public List<Osoba> getGosti() {
		return gosti;
	}
	public void setGosti(List<Osoba> gosti) {
		this.gosti = gosti;
	}
	public Soba getSoba() {
		return soba;
	}
	public void setSoba(Soba soba) {
		this.soba = soba;
	}
	public LocalDateTime getDatumPocetka() {
		return datumPocetka;
	}
	public void setDatumPocetka(LocalDateTime datumPocetka) {
		this.datumPocetka = datumPocetka;
	}
	public LocalDateTime getDatumZavrsetka() {
		return datumZavrsetka;
	}
	public void setDatumZavrsetka(LocalDateTime datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
}
