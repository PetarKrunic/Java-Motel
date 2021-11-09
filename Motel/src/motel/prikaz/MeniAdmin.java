package motel.prikaz;


import motel.kontroler.OsobaKontroler;
import motel.model.Menadzer;
import motel.utils.Inputer;
import motel.utils.Printer;

public class MeniAdmin {
	
	private OsobaKontroler osobaKontroler;
	
	public MeniAdmin(OsobaKontroler osobaKontroler) {
		this.osobaKontroler = osobaKontroler;
	}
	
	
	
	public void pokreni() {
		Loop:while(true) {
			System.out.println("\n~~~~~Meni~~~~~\n");
			System.out.println(" 1. Pregled menadžera");
			System.out.println(" 2. Dodaj menadžera");
			System.out.println(" 3. Izmeni menadžera");
			System.out.println(" 4. Brisanje menadžera");
			System.out.println(" 5. Izloguj se");
			System.out.println(" 0. Izlaz ");
			System.out.print("\n Izaberite opciju (0-5): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{
					System.exit(0);
				}
				case 1:{
					
					System.out.println("\n\t~Menadžeri~\n");
					Printer.printKorisnikZaglavlje();
					osobaKontroler.getMenadzeri().stream().forEach(m -> Printer.printKorisnik(m));
					System.out.println(" 1. Sortiranje menadzera");
					System.out.println(" 2. Pretraga menadzera");
					System.out.println(" 0. Nazad");
					System.out.print("\n Izaberite opciju (0-2): ");
					int unos = Inputer.getInt();
					switch(unos) {
						case 1:{
							System.out.println(" 1. Sortiranje menadzera po imenu i prezimenu");
							System.out.println(" 2. Sortiranje menadzera po korisnickom imenu");
							System.out.println(" 0. Nazad");
							System.out.print("\n Izaberite opciju (0-2): ");
							int sort = Inputer.getInt();
							if(sort == 1) {
								System.out.println("\n\t~Sortirani menadzeri po imenu i prezimenu~\n");
								Printer.printKorisnikZaglavlje();
								osobaKontroler.getSortiraniKorisniciPoImenuIPrezimenu()
									.stream()
									.filter(k -> k instanceof Menadzer )
									.forEach(r -> Printer.printKorisnik(r));
							}else if(sort ==2) {
								System.out.println("\n\t~Sortirani menadzeri po korisnickom imenu~\n");
								Printer.printKorisnikZaglavlje();
								osobaKontroler.getSortiraniKorisniciPoKorisnickomImenu()
								.stream()
								.filter(k -> k instanceof Menadzer )
								.forEach(r -> Printer.printKorisnik(r));
							}
							
						}
						case 2:{
							
						}
					}
					break;
				}
				case 2:{
					
					System.out.println("\n\t~Dodavanje menadžera~\n");
					System.out.print(" Ime: ");
					String ime = Inputer.getString();
					
					System.out.print(" Prezime: ");
					String prezime = Inputer.getString();
					
					System.out.print(" Broj lične karte: ");
					String brojLicneKarte= Inputer.getString();
					
					System.out.print(" Korisničko ime: ");
					String korisnickoIme = Inputer.getString();
					
					System.out.print(" Lozinka: ");
					String lozinka = Inputer.getString();
					
					Menadzer noviMenadzer = new Menadzer(ime, prezime, brojLicneKarte, korisnickoIme, lozinka);
					boolean uspesnoDodavanje = osobaKontroler.dodajOsobu(noviMenadzer);
					if(!uspesnoDodavanje) {
						System.out.println("Postoji korisnik sa korisnickim imenom ili sa brojem licne karte!");
					}
					break;
				}

				case 3:{
					System.out.println("\n\t~Izmjena menadžera~\n");
					Printer.printKorisnikZaglavlje();
					osobaKontroler.getMenadzeri().stream().forEach(m -> Printer.printKorisnik(m));
					System.out.print("\n Uneseti korisničko ime menadžera za izmenu:");
					String korisnickoIme =Inputer.getString();
					Menadzer menadzer = osobaKontroler.getMenadzer(korisnickoIme);
					if(menadzer ==null ) {
						System.out.println("Korisničko ime '"+korisnickoIme+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Ime("+menadzer.getIme()+"): ");
					String ime = Inputer.getString();
					System.out.print(" Prezime("+menadzer.getPrezime()+"): ");
					String prezime = Inputer.getString();
					System.out.print(" Broj lične karte("+menadzer.getBrojLicneKarte()+"): ");
					String brojLicneKarte= Inputer.getString();
					System.out.print(" Korisničko ime("+menadzer.getKorisnickoIme()+"): ");
					String korisnickoIme2 = Inputer.getString();
					System.out.print(" Lozinka("+menadzer.getLozinka()+"): ");
					String lozinka = Inputer.getString();
					
					Menadzer noviMenadzer = new Menadzer(ime, prezime, brojLicneKarte, korisnickoIme2, lozinka);
					boolean uspesnaIzmena = osobaKontroler.izmeniKorisnika(menadzer, noviMenadzer );
					
					if(!uspesnaIzmena) {
						System.out.println("Izmena neuspešna! Korisničko ime '"+korisnickoIme2+"' već postoji!");

					}
					
					break;
				}
				
				case 4:{
					System.out.println("\n\t~Brisanje menadžera~\n");
					Printer.printKorisnikZaglavlje();
					osobaKontroler.getMenadzeri().stream().forEach(m -> Printer.printKorisnik(m));
					System.out.print("\n Uneseti korisničko ime menadžera za brisanje:");
					String korisnickoIme =Inputer.getString();
					boolean uspesnoBrisanje = osobaKontroler.obrisiOsobu(korisnickoIme);
					if(!uspesnoBrisanje) {
						System.out.println("Brisanje neuspešno! Korisničko ime '"+korisnickoIme+"' ne postoji!");
					}
					break;
				}
				case 5:{
					
					break Loop;
				}
				
			}
		}

	}
}
