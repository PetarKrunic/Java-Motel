package motel.model;

import java.util.ArrayList;
import java.util.List;

public class Menadzer extends Korisnik {

	private List<StavkaCenovnika> dodateStavke;
	
	public Menadzer(String ime, String prezime, String brojLicneKarte, String korisnickoIme, String lozinka) {
		super(ime, prezime, brojLicneKarte, korisnickoIme, lozinka);
		// TODO Auto-generated constructor stub
		this.dodateStavke = new ArrayList<>();
	}
	
	public Menadzer(String ime, String prezime, String brojLicneKarte, boolean obrisan, String korisnickoIme,
			String lozinka, List<StavkaCenovnika> dodateStavke) {
		super(ime, prezime, brojLicneKarte, obrisan, korisnickoIme, lozinka);
		this.dodateStavke = dodateStavke;
	}


	@Override
	public String ispis() {
		String ispis = super.ispis();
		for(StavkaCenovnika stavka : dodateStavke) {
			ispis += ";"+stavka.getDatumKreiranja().toString(); 
		}
		return ispis;	
	}
	

	public List<StavkaCenovnika> getDodateStavke() {
		return dodateStavke;
	}

	public void setDodateStavke(List<StavkaCenovnika> dodateStavke) {
		this.dodateStavke = dodateStavke;
	}
	
	
	
	
	
}
