package motel.model;

import java.util.ArrayList;
import java.util.List;

public class Recepcioner extends Korisnik {

	private List<Racun> izdatiRacuni;
	
	public Recepcioner(String ime, String prezime, String brojLicneKarte, String korisnickoIme, String lozinka) {
		super(ime, prezime, brojLicneKarte, korisnickoIme, lozinka);
		this.izdatiRacuni = new ArrayList<>();
	}
	
	
	
	public Recepcioner(String ime, String prezime, String brojLicneKarte, boolean obrisan, String korisnickoIme,
			String lozinka, List<Racun> izdatiRacuni) {
		super(ime, prezime, brojLicneKarte, obrisan, korisnickoIme, lozinka);
		this.izdatiRacuni = izdatiRacuni;
	}



	@Override
	public String ispis() {
		String ispis = super.ispis()+"|";
		for(Racun racun : izdatiRacuni) {
			ispis += racun.getDatumIzdavanja() + ";"; 
		}
		ispis = ispis.substring(0,ispis.length()-1);
		return ispis;	
	}

	public List<Racun> getIzdatiRacuni() {
		return izdatiRacuni;
	}

	public void setIzdatiRacuni(List<Racun> izdatiRacuni) {
		this.izdatiRacuni = izdatiRacuni;
	}
	
	

}
