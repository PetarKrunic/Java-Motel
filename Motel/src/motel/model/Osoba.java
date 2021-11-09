package motel.model;

public class Osoba {

	protected String ime;
	protected String prezime;
	protected String brojLicneKarte;
	protected boolean obrisan;

	public Osoba(String ime, String prezime, String brojLicneKarte) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brojLicneKarte = brojLicneKarte;
		this.obrisan = false;
	}
	
	
	
	public Osoba(String ime, String prezime, String brojLicneKarte, boolean obrisan) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.brojLicneKarte = brojLicneKarte;
		this.obrisan = obrisan;
	}



	public String ispis() {
	
		return getClass().getSimpleName()+"|"+obrisan+"|"+ime + "|" +prezime+"|"+brojLicneKarte;
	}
	
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojLicneKarte() {
		return brojLicneKarte;
	}
	public void setBrojLicneKarte(String brojLicneKarte) {
		this.brojLicneKarte = brojLicneKarte;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
}
