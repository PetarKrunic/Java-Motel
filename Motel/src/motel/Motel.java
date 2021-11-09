package motel;


import motel.dao.CenovnikDAO;
import motel.dao.IznajmljivanjeDAO;
import motel.dao.OsobaDAO;
import motel.dao.SobaDAO;
import motel.dao.TipSobeDAO;
import motel.dao.RacunDAO;
import motel.kontroler.CenovnikKontroler;
import motel.kontroler.IznajmljivanjeKontroler;
import motel.kontroler.OsobaKontroler;
import motel.kontroler.RacunKontroler;
import motel.kontroler.SobaKontroler;
import motel.kontroler.TipSobeKontroler;
import motel.prikaz.Meni;

public class Motel {

	public static void main(String[] args) {
		
		TipSobeDAO tipSobeDAO = new TipSobeDAO();
		TipSobeKontroler tipSobeKontroler = new TipSobeKontroler(tipSobeDAO);
		
		CenovnikDAO cenovnikaDAO = new CenovnikDAO(tipSobeDAO);
		CenovnikKontroler cenovnikKontroler = new CenovnikKontroler(cenovnikaDAO);
		
		SobaDAO sobaDAO = new SobaDAO(tipSobeDAO);
		SobaKontroler sobaKontroler = new SobaKontroler(sobaDAO);
		
		IznajmljivanjeDAO iznajmljivanjeDAO = new IznajmljivanjeDAO (sobaDAO);
		IznajmljivanjeKontroler iznajmljivanjeKontroler = new IznajmljivanjeKontroler(iznajmljivanjeDAO);
		
		RacunDAO racunDAO = new RacunDAO(iznajmljivanjeDAO);
		RacunKontroler racunKontroler  = new RacunKontroler(racunDAO, cenovnikaDAO);
		
		OsobaDAO osobaDAO = new OsobaDAO(cenovnikaDAO, racunDAO);
		OsobaKontroler osobaKontroler = new OsobaKontroler(osobaDAO);
		
		Meni meni = new Meni(osobaKontroler, tipSobeKontroler, sobaKontroler, cenovnikKontroler, iznajmljivanjeKontroler, racunKontroler);
		meni.pokreni();
		
	}

}
