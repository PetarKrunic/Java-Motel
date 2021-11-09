package motel.model;

public class TipSobe {

	private String naziv;
	private int brojKreveta;
	private boolean obrisan;
	
	public TipSobe(String naziv, int brojKreveta) {
		super();
		this.naziv = naziv;
		this.brojKreveta = brojKreveta;
		this.obrisan= false;
	}
	
	public TipSobe(String naziv, int brojKreveta, boolean obrisan) {
		super();
		this.naziv = naziv;
		this.brojKreveta = brojKreveta;
		this.obrisan= obrisan;
	}
	
	public String ispis() {
		return obrisan + "|" + naziv + "|" + brojKreveta;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getBrojKreveta() {
		return brojKreveta;
	}
	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
}
