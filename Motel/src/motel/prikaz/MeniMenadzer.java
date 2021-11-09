package motel.prikaz;

import java.util.List;

import motel.kontroler.CenovnikKontroler;
import motel.kontroler.OsobaKontroler;
import motel.kontroler.SobaKontroler;
import motel.kontroler.TipSobeKontroler;
import motel.model.Recepcioner;
import motel.model.Soba;
import motel.model.StavkaCenovnika;
import motel.model.TipSobe;
import motel.utils.Inputer;
import motel.utils.Printer;

public class MeniMenadzer {

	private OsobaKontroler osobaKontroler;
	private TipSobeKontroler tipSobeKontroler;
	private SobaKontroler sobaKontroler;
	private CenovnikKontroler cenovnikKontroler;
	
	public MeniMenadzer(OsobaKontroler osobaKontroler,
						TipSobeKontroler tipSobeKontroler, 
						SobaKontroler sobaKontroler,
						CenovnikKontroler cenovnikKontroler) {
		this.osobaKontroler = osobaKontroler;
		this.tipSobeKontroler = tipSobeKontroler;
		this.sobaKontroler = sobaKontroler;
		this.cenovnikKontroler = cenovnikKontroler;
	}
	
	public void pokreni() {
		Loop:while(true) {
			System.out.println("\n~~~~~Meni~~~~~\n");
			System.out.println(" 1. Administracija recepcionera");
			System.out.println(" 2. Administracija tipova soba");
			System.out.println(" 3. Administracija cenovnika");
			System.out.println(" 4. Administracija soba");
			System.out.println(" 5. Izmeni profil");
			System.out.println(" 6. Izloguj se");
			System.out.println(" 0. Izlaz ");
			System.out.print("\n Izaberite opciju (0-6): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{
					System.exit(0);
				}
				case 1:{
					pokreniPodMeniRecepcioner();
					break;
				}
				case 2:{
					pokreniPodMeniTipSobe();
					break;
				}
				case 3:{
					pokreniPodMeniCenovnik();

					break;
				}
				
				case 4:{
					pokreniPodMeniSoba();
					break;
				}
				case 5:{
					
				}
				case 6:{
					break Loop;

				}
			}
		}
	

	}
		
	private void pokreniPodMeniTipSobe() {
		Loop:while(true) {
			System.out.println("\n~~~~~Adminstracija tipova soba~~~~~\n");
			System.out.println(" 1. Pregled tipova soba");
			System.out.println(" 2. Dodavanje tipa sobe");
			System.out.println(" 3. Izmena tipa sobe");
			System.out.println(" 4. Brisanje tipa sobe");
			System.out.println(" 0. Nazad u glavni meni ");
			System.out.print("\n Izaberite opciju (0-4): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{				
					break Loop;
				}
				case 1:{
					
					System.out.println("\n\t~Tipovi soba~\n");
					Printer.printTipSobeZaglavlje();
					tipSobeKontroler.getTipoviSoba().stream().forEach(t -> Printer.printTipSobe(t));
					System.out.println(" 1. Sortiranje tip soba");
					System.out.println(" 2. Pretraga tip soba");
					System.out.println(" 0. Nazad");
					System.out.print("\n Izaberite opciju (0-2): ");
					int unos = Inputer.getInt();
					switch(unos) {
						case 1:{
							System.out.println(" 1. Sortiranje tip soba po broju kreveta");
							System.out.println(" 2. Sortiranje tip soba po naziv");
							System.out.println(" 3. Sortiranje tip soba po naziv i broju");
							System.out.println(" 0. Nazad");
							System.out.print("\n Izaberite opciju (0-3): ");
							int sort = Inputer.getInt();
							if(sort == 1) {
								System.out.println("\n\t~Sortirani tipovi soba po broju kreveta~\n");
								Printer.printTipSobeZaglavlje();
								tipSobeKontroler.getSortiraneTipoviSobaPoBroju().stream().forEach(t -> Printer.printTipSobe(t));
							}else if(sort ==2) {
								System.out.println("\n\t~Sortirani tipovi soba po nazivu~\n");
								Printer.printTipSobeZaglavlje();
								tipSobeKontroler.getSortiraneTipoviSobaPoNazivu().stream().forEach(t -> Printer.printTipSobe(t));
							}else if(sort == 3) {
								System.out.println("\n\t~Sortirani tipovi soba po nazivu i broju kreveta~\n");
								Printer.printTipSobeZaglavlje();
								tipSobeKontroler.getSortiraneTipoviSobaPoNazivuIBroju().stream().forEach(t -> Printer.printTipSobe(t));
							}
							
							
						}
						case 2:{
							
						}
					}
					break;
				}
				case 2:{
					
					System.out.println("\n\t~Dodavanje tipa sobe~\n");
					System.out.print(" Naziv: ");
					String naziv = Inputer.getString();
					
					System.out.print(" BrojKreveta: ");
					int brojKreveta = Inputer.getInt();
					
					TipSobe tipSobe = new TipSobe(naziv, brojKreveta);
					boolean uspesnoDodavanje = tipSobeKontroler.dodajTipSobe(tipSobe);	
					if(!uspesnoDodavanje) {
						System.out.println("Postoji korisnik sa korisnickim imenom ili sa brojem licne karte!");
					}
					break;
				}

				case 3:{
					System.out.println("\n\t~Izmena tipa sobe~\n");
					System.out.print("   |");
					Printer.printTipSobeZaglavlje();
					for(int i = 0; i< tipSobeKontroler.getTipoviSoba().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printTipSobe(tipSobeKontroler.getTipSobe(i));
					}
					System.out.print("\n Uneseti redni broj tipa sobe za izmenu:");
					int redniBroj =Inputer.getInt();
					TipSobe tipSobe = tipSobeKontroler.getTipSobe(redniBroj-1);
					if(tipSobe ==null ) {
						System.out.println("Redni broj '"+redniBroj+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Naziv("+tipSobe.getNaziv()+"): ");
					String naziv = Inputer.getString();
					System.out.print(" Broj kreveta("+tipSobe.getBrojKreveta()+"): ");
					int brojKreveta = Inputer.getInt();
	
					TipSobe noviTipSobe = new TipSobe(naziv, brojKreveta);
					boolean uspesnaIzmena = tipSobeKontroler.izmeniTipSobe(tipSobe, noviTipSobe);
					if(!uspesnaIzmena) {
						System.out.println("Izmena neuspešna! Tip sobe već postoji!");
					}					
					break;
				}
				
				case 4:{
					System.out.println("\n\t~Brisanje recepcionera~\n");
					System.out.print("   |");
					Printer.printTipSobeZaglavlje();
					for(int i = 0; i< tipSobeKontroler.getTipoviSoba().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printTipSobe(tipSobeKontroler.getTipSobe(i));
					}
					System.out.print("\n Uneseti redni broj tipa sobe za brisanje:");
					int redniBroj =Inputer.getInt();
					boolean uspesnoBrisanje = tipSobeKontroler.obrisiTipSobe(redniBroj-1);
					if(!uspesnoBrisanje) {
						System.out.println("Brisanje neuspešno! Redni broj " +redniBroj+" ne postoji!");
					}
					break;
				}
			}
		}
		
	}

	public void pokreniPodMeniSoba() {
		Loop:while(true) {
			System.out.println("\n~~~~~Adminstracija soba~~~~~\n");
			System.out.println(" 1. Pregled soba");
			System.out.println(" 2. Dodaj sobu");
			System.out.println(" 3. Izmeni sobu");
			System.out.println(" 4. Brisanje sobe");
			System.out.println(" 0. Nazad u glavni meni ");
			System.out.print("\n Izaberite opciju (0-4): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{				
					break Loop;
				}
				case 1:{
					
					System.out.println("\n\t~Sobe~\n");
					Printer.printSobaZaglavlje();
					sobaKontroler.getSobe().stream().forEach(s -> Printer.printSoba(s));
					System.out.println(" 1. Sortiranje soba");
					System.out.println(" 2. Pretraga soba");
					System.out.println(" 0. Nazad");
					System.out.print("\n Izaberite opciju (0-2): ");
					int unos = Inputer.getInt();
					switch(unos) {
						case 1:{
							System.out.println(" 1. Sortiranje soba po broju");
							System.out.println(" 2. Sortiranje soba po tipu");
							System.out.println(" 0. Nazad");
							System.out.print("\n Izaberite opciju (0-2): ");
							int sort = Inputer.getInt();
							if(sort == 1) {
								System.out.println("\n\t~Sortirane sobe po broju~\n");
								Printer.printSobaZaglavlje();
								sobaKontroler.getSortiraneSobePoBroju().stream().forEach(s -> Printer.printSoba(s));
							}else if(sort ==2) {
								System.out.println("\n\t~Sortirane sobe po tipu~\n");
								Printer.printSobaZaglavlje();
								sobaKontroler.getSortiraneSobePoTipu().stream().forEach(s -> Printer.printSoba(s));
							}
							
						}
						case 2:{
							System.out.println("\n\t~Pretraga sobe~\n");
							System.out.print(" Broj sobe: ");
							String brojSobe = Inputer.getEmptyOrString();
							
							System.out.print(" Broj kreveta: ");
							String brojKreveta = Inputer.getEmptyOrString();
							
							System.out.print(" TV (da/ne): ");
							String tv= Inputer.getEmptyOrString();
							
							System.out.print(" Mini bar (da/ne): ");
							String miniBar= Inputer.getEmptyOrString();
							
							List<Soba> sobePretrage = sobaKontroler.pretraga(brojSobe,brojKreveta,tv, miniBar); 
							System.out.println("\n\t~Pretrazene sobe~\n");
							Printer.printSobaZaglavlje();
							sobePretrage.stream().forEach(s -> Printer.printSoba(s));
						}
					}
					break;
				}
				case 2:{
					
					System.out.println("\n\t~Dodavanje sobe~\n");
					System.out.print(" Broj sobe: ");
					String broj = Inputer.getString();
					
					System.out.print(" TV (da/ne): ");
					String tvString= Inputer.getString();
					boolean tv = tvString.equalsIgnoreCase("da"); 
					
					System.out.print(" Mini bar (da/ne): ");
					String miniBarString= Inputer.getString();
					boolean miniBar = miniBarString.equalsIgnoreCase("da");
					
					System.out.print("   |");
					Printer.printTipSobeZaglavlje();
					for(int i = 0; i< tipSobeKontroler.getTipoviSoba().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printTipSobe(tipSobeKontroler.getTipSobe(i));
					}
					System.out.print("\n Izberite redni broj tipa sobe:");
					int redniBroj =Inputer.getInt();

					TipSobe tipSobe = tipSobeKontroler.getTipSobe(redniBroj-1);
					if(tipSobe == null) {
						System.out.println("Ne postoji redni broj koji ste uneli!");
						continue;
					}
					
					Soba soba = new Soba(broj, tv, miniBar, tipSobe);
					boolean uspesnoDodavanje = sobaKontroler.dodajSobu(soba);	
					if(!uspesnoDodavanje) {
						System.out.println("Neuspešno dodavanje! Postoji soba sa brojem + " + broj+"!");
					}
					break;
				}

				case 3:{
					System.out.println("\n\t~Izmena sobe~\n");
					Printer.printSobaZaglavlje();
					sobaKontroler.getSobe().stream().forEach(s -> Printer.printSoba(s));
					System.out.print("\n Uneseti broj sobe za izmenu:");
					String brojSobe =Inputer.getString();
					Soba soba = sobaKontroler.getSoba(brojSobe);
					if(soba ==null ) {
						System.out.println("Soba sa brojem '"+brojSobe+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Broj sobe("+soba.getBroj()+"): ");
					String broj = Inputer.getString();
					
					String tvOld = soba.isTv() ? "da":"ne"; 
					System.out.print(" TV ("+tvOld+") (da/ne): ");
					String tvString= Inputer.getString();
					boolean tv = tvString.equalsIgnoreCase("da"); 
					
					String miniBarOld = soba.isMiniBar() ? "da":"ne";
					System.out.print(" Mini bar ("+miniBarOld+") (da/ne): ");
					String miniBarString= Inputer.getString();
					boolean miniBar = miniBarString.equalsIgnoreCase("da");
					
					System.out.print("   |");
					Printer.printTipSobeZaglavlje();
					int stariTipSobe=0;
					for(int i = 0; i< tipSobeKontroler.getTipoviSoba().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printTipSobe(tipSobeKontroler.getTipSobe(i));
						TipSobe tipSobe = tipSobeKontroler.getTipSobe(i);
						if(tipSobe.getNaziv().equals(soba.getTipSobe().getNaziv()) && tipSobe.getBrojKreveta() == soba.getTipSobe().getBrojKreveta()) {
							stariTipSobe = i+1;
						}
					}
					System.out.print("\n Izberite redni broj tipa sobe("+stariTipSobe+"):");
					int redniBroj =Inputer.getInt();

					TipSobe tipSobe = tipSobeKontroler.getTipSobe(redniBroj-1);
					if(tipSobe == null) {
						System.out.println("Ne postoji redni broj koji ste uneli!");
						continue;
					}
	
					Soba novaSoba= new Soba(broj, tv, miniBar, tipSobe);
					boolean uspesnaIzmena = sobaKontroler.izmeniSobu(soba, novaSoba);
					if(!uspesnaIzmena) {
						System.out.println("Izmena neuspešna! Soba već postoji!");
					}					
					break;
				}
				
				case 4:{
					System.out.println("\n\t~Brisanje sobe~\n");
					Printer.printSobaZaglavlje();
					sobaKontroler.getSobe().stream().forEach(s -> Printer.printSoba(s));
					System.out.print("\n Uneseti broj sobe za brisanje:");
					String brojSobe =Inputer.getString();
					boolean uspesnoBrisanje = sobaKontroler.obrisiSobu(brojSobe);
					if(!uspesnoBrisanje) {
						System.out.println("Brisanje neuspešno! Soba sa brojem '" +brojSobe+"' ne postoji!");
					}
					break;
				}
			}
		}
	}
	
	public void pokreniPodMeniCenovnik() {
		Loop:while(true) {
			System.out.println("\n~~~~~Adminstracija cenovnika~~~~~\n");
			System.out.println(" 1. Pregled cenovnika");
			System.out.println(" 2. Dodavanje stavke cenovnika");
			System.out.println(" 3. Izmena stavke cenovnika");
			System.out.println(" 4. Brisanje stakve cenovnika");
			System.out.println(" 0. Nazad u glavni meni ");
			System.out.print("\n Izaberite opciju (0-4): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{				
					break Loop;
				}
				case 1:{
					
					System.out.println("\n\t~Stavke cenovnika~\n");
					Printer.printCenovnikZaglavlje();
					cenovnikKontroler.getCenovnik().stream().forEach(sc -> Printer.printCenovnik(sc));
					break;
				}
				case 2:{
					
					System.out.println("\n\t~Dodavanje stavke cenovnika~\n");
					System.out.print(" Cena dnevnog boravka: ");
					double dnevniBoravak = Inputer.getDouble();
					
					System.out.print(" Cena nocenja: ");
					double nocenje = Inputer.getDouble();
					
					System.out.print(" Vikend poskupljenje u procentima: ");
					double vikendPoskupljenje = Inputer.getDouble();
					
					System.out.print("   |");
					Printer.printTipSobeZaglavlje();
					for(int i = 0; i< tipSobeKontroler.getTipoviSoba().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printTipSobe(tipSobeKontroler.getTipSobe(i));
					}
					System.out.print("\n Izberite redni broj tipa sobe:");
					int redniBroj =Inputer.getInt();

					TipSobe tipSobe = tipSobeKontroler.getTipSobe(redniBroj-1);
					if(tipSobe == null) {
						System.out.println("Ne postoji redni broj koji ste uneli!");
						continue;
					}
					
					StavkaCenovnika stavkaCenovnika = new StavkaCenovnika(dnevniBoravak, nocenje, vikendPoskupljenje, tipSobe);
					cenovnikKontroler.dodajStavku(stavkaCenovnika);
					
					osobaKontroler.dodajStavkuCenovnikaMenadzeru(stavkaCenovnika);
					//TODO SACUVAJ I DODAJ KOD IZMENE i BRISANJA
					break;
				}

				case 3:{
					System.out.println("\n\t~Izmena stavke cenovnika~\n");
					Printer.printCenovnikZaglavlje();
					cenovnikKontroler.getCenovnik().stream().forEach(sc -> Printer.printCenovnik(sc));
					System.out.print("\n Uneseti broj stavke cenovnika za izmenu:");
					int redniBroj =Inputer.getInt();
					StavkaCenovnika stavkaCenovnika = cenovnikKontroler.getStavka(redniBroj -1 );
					if(stavkaCenovnika ==null ) {
						System.out.println("Redni broj '"+redniBroj+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Cena dnevnog boravka("+stavkaCenovnika.getDnevniBoravak()+"): ");
					double dnevniBoravak = Inputer.getDouble();
					
					System.out.print(" Cena nocenja("+stavkaCenovnika.getNocenje()+"): ");
					double nocenje = Inputer.getDouble();
					
					System.out.print(" Vikend poskupljenje u procentima("+stavkaCenovnika.getVikendPoskupljenje()+"): ");
					double vikendPoskupljenje = Inputer.getDouble();
										
					System.out.print("   |");
					Printer.printTipSobeZaglavlje();
					int stariTipSobe=0;
					for(int i = 0; i< tipSobeKontroler.getTipoviSoba().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printTipSobe(tipSobeKontroler.getTipSobe(i));
						TipSobe tipSobe = tipSobeKontroler.getTipSobe(i);
						if(tipSobe.getNaziv().equals(stavkaCenovnika.getTipSobe().getNaziv()) && tipSobe.getBrojKreveta() == stavkaCenovnika.getTipSobe().getBrojKreveta()) {
							stariTipSobe = i+1;
						}
					}
					System.out.print("\n Izberite redni broj tipa sobe("+stariTipSobe+"):");
					int redniBrojTipa =Inputer.getInt();

					TipSobe tipSobe = tipSobeKontroler.getTipSobe(redniBrojTipa-1);
					if(tipSobe == null) {
						System.out.println("Ne postoji redni broj koji ste uneli!");
						continue;
					}
	
					StavkaCenovnika novaStavkaCenovnika = new StavkaCenovnika(dnevniBoravak, nocenje, vikendPoskupljenje, tipSobe);
					cenovnikKontroler.izmeniStavku(stavkaCenovnika, novaStavkaCenovnika);					
					break;
				}
				
				case 4:{
					System.out.println("\n\t~Brisanje stavke cenovnika~\n");
					Printer.printCenovnikZaglavlje();
					cenovnikKontroler.getCenovnik().stream().forEach(sc -> Printer.printCenovnik(sc));
					System.out.print("\n Uneseti redni broj stavke cenovnika za brisanje:");
					int redniBroj =Inputer.getInt();
					boolean uspesnoBrisanje = cenovnikKontroler.obrisiStavku(redniBroj);
					if(!uspesnoBrisanje) {
						System.out.println("Brisanje neuspešno! RedniBroj'" +redniBroj+"' ne postoji!");
					}
					break;
				}
			}
		}
	}
	
	public void pokreniPodMeniRecepcioner() {
		Loop:while(true) {
			System.out.println("\n~~~~~Adminstracija recepcionera~~~~~\n");
			System.out.println(" 1. Pregled recepcionera");
			System.out.println(" 2. Dodaj recepcinera");
			System.out.println(" 3. Izmeni recepcionera");
			System.out.println(" 4. Brisanje recepcionera");
			System.out.println(" 0. Nazad u glavni meni ");
			System.out.print("\n Izaberite opciju (0-4): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{				
					break Loop;
				}
				case 1:{
					
					System.out.println("\n\t~Recepcioneri~\n");
					Printer.printKorisnikZaglavlje();
					osobaKontroler.getRecepcionari().stream().forEach(r -> Printer.printKorisnik(r));
					System.out.println(" 1. Sortiranje recepcionera");
					System.out.println(" 2. Pretraga recepcionera");
					System.out.println(" 0. Nazad");
					System.out.print("\n Izaberite opciju (0-2): ");
					int unos = Inputer.getInt();
					switch(unos) {
						case 1:{
							System.out.println(" 1. Sortiranje recepcionera po imenu i prezimenu");
							System.out.println(" 2. Sortiranje recepcionera po korisnickom imenu");
							System.out.println(" 0. Nazad");
							System.out.print("\n Izaberite opciju (0-2): ");
							int sort = Inputer.getInt();
							if(sort == 1) {
								System.out.println("\n\t~Sortirani recepcioneri po imenu i prezimenu~\n");
								Printer.printKorisnikZaglavlje();
								osobaKontroler.getSortiraniKorisniciPoImenuIPrezimenu()
									.stream()
									.filter(k -> k instanceof Recepcioner )
									.forEach(r -> Printer.printKorisnik(r));
							}else if(sort ==2) {
								System.out.println("\n\t~Sortirani recepcioneri po korisnickom imenu~\n");
								Printer.printKorisnikZaglavlje();
								osobaKontroler.getSortiraniKorisniciPoKorisnickomImenu()
								.stream()
								.filter(k -> k instanceof Recepcioner )
								.forEach(r -> Printer.printKorisnik(r));
							}
							
						}
						case 2:{
							
						}
					}
					break;
				}
				case 2:{
					
					System.out.println("\n\t~Dodavanje recepcionera~\n");
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
					
					Recepcioner noviRecepcioner = new Recepcioner(ime, prezime, brojLicneKarte, korisnickoIme, lozinka);
					boolean uspesnoDodavanje = osobaKontroler.dodajOsobu(noviRecepcioner);
					if(!uspesnoDodavanje) {
						System.out.println("Postoji korisnik sa korisnickim imenom ili sa brojem licne karte!");
					}
					break;
				}

				case 3:{
					System.out.println("\n\t~Izmena recepcionera~\n");
					Printer.printKorisnikZaglavlje();
					osobaKontroler.getRecepcionari().stream().forEach(m -> Printer.printKorisnik(m));
					System.out.print("\n Uneseti korisničko ime recepcionara za izmenu:");
					String korisnickoIme =Inputer.getString();
					Recepcioner recepcioner = osobaKontroler.getRecepcioner(korisnickoIme);
					if(recepcioner ==null ) {
						System.out.println("Korisničko ime '"+korisnickoIme+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Ime("+recepcioner.getIme()+"): ");
					String ime = Inputer.getString();
					System.out.print(" Prezime("+recepcioner.getPrezime()+"): ");
					String prezime = Inputer.getString();
					System.out.print(" Broj lične karte("+recepcioner.getBrojLicneKarte()+"): ");
					String brojLicneKarte= Inputer.getString();
					System.out.print(" Korisničko ime("+recepcioner.getKorisnickoIme()+"): ");
					String korisnickoIme2 = Inputer.getString();
					System.out.print(" Lozinka("+recepcioner.getLozinka()+"): ");
					String lozinka = Inputer.getString();
					
					Recepcioner noviRecepcioner = new Recepcioner(ime, prezime, brojLicneKarte, korisnickoIme2, lozinka);
					boolean uspesnaIzmena = osobaKontroler.izmeniKorisnika(recepcioner, noviRecepcioner );
					
					if(!uspesnaIzmena) {
						System.out.println("Izmena neuspešna! Korisničko ime '"+korisnickoIme2+"' već postoji!");
					}					
					break;
				}
				
				case 4:{
					System.out.println("\n\t~Brisanje recepcionera~\n");
					Printer.printKorisnikZaglavlje();
					osobaKontroler.getRecepcionari().stream().forEach(m -> Printer.printKorisnik(m));
					System.out.print("\n Uneseti korisničko ime recepcionera za brisanje:");
					String korisnickoIme =Inputer.getString();
					boolean uspesnoBrisanje = osobaKontroler.obrisiOsobu(korisnickoIme);
					if(!uspesnoBrisanje) {
						System.out.println("Brisanje neuspešno! Korisničko ime '"+korisnickoIme+"' ne postoji!");
					}
					break;
				}
				
				
			}
		}

	}
}
