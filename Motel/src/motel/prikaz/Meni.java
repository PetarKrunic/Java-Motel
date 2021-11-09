package motel.prikaz;

import motel.kontroler.CenovnikKontroler;
import motel.kontroler.IznajmljivanjeKontroler;
import motel.kontroler.OsobaKontroler;
import motel.kontroler.RacunKontroler;
import motel.kontroler.SobaKontroler;
import motel.kontroler.TipSobeKontroler;
import motel.model.Korisnik;
import motel.model.Menadzer;
import motel.model.Recepcioner;
import motel.utils.Inputer;

public class Meni {

	private OsobaKontroler osobaKontroler;

	private MeniAdmin meniAdmin;
	private MeniMenadzer meniMenadzer;
	private MeniRecepcioner meniRecepcioner;
	
	public Meni(OsobaKontroler osobaKontroler,TipSobeKontroler tipSobeKontroler,
				SobaKontroler sobaKontroler, CenovnikKontroler cenovnikKontroler, 
				IznajmljivanjeKontroler iznajmljivanjeKontroler, RacunKontroler racunKontroler) {
		this.osobaKontroler = osobaKontroler;

		this.meniAdmin = new MeniAdmin(osobaKontroler);
		this.meniMenadzer = new MeniMenadzer(osobaKontroler,tipSobeKontroler,sobaKontroler,cenovnikKontroler);
		this.meniRecepcioner = new MeniRecepcioner(osobaKontroler,sobaKontroler,iznajmljivanjeKontroler, racunKontroler);
	}
	
	public void pokreni() {
		System.out.println("~~~~~Logovanje~~~~~");
		while(true) {
			System.out.print("\nKorisnicko ime: ");
			String korisnickoIme = Inputer.getString();
			System.out.print("Lozinka: ");
			String lozinka = Inputer.getString();
			Korisnik korisnik = osobaKontroler.logovanje(korisnickoIme, lozinka);
			if(korisnik == null) {
				System.out.print("Neuspešno logovanje! Da li želite da ponovo da ulogujete?(Da/Ne)");
				String ponovo = Inputer.getString();
				if(ponovo.equalsIgnoreCase("da")) {
					continue;
				}else {
					break;
				}
			}
			System.out.println("\nUspešno ste se ulogovali kao "+korisnik.getIme() + " " + korisnik.getPrezime());
			
			if(korisnik instanceof Menadzer) {
				meniMenadzer.pokreni();
			}else if( korisnik instanceof Recepcioner) {
				meniRecepcioner.pokreni();
			}else {
				meniAdmin.pokreni();
			}
		}
		
	}
	
}
