package motel.model;

public class Korisnik extends Osoba{

	private String korisnickoIme;
	private String lozinka;
	
	
	
	public Korisnik(String ime, String prezime, String brojLicneKarte, String korisnickoIme, String lozinka) {
		super(ime, prezime, brojLicneKarte);
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	
	
	
	public Korisnik(String ime, String prezime, String brojLicneKarte, boolean obrisan, String korisnickoIme,
			String lozinka) {
		super(ime, prezime, brojLicneKarte, obrisan);
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}



	@Override
	public String ispis() {
		return super.ispis() + "|" + korisnickoIme + "|" + lozinka;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	
}
