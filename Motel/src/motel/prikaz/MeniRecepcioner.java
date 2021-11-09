package motel.prikaz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import motel.kontroler.IznajmljivanjeKontroler;
import motel.kontroler.OsobaKontroler;
import motel.kontroler.RacunKontroler;
import motel.kontroler.SobaKontroler;
import motel.model.Iznajmljivanje;
import motel.model.Osoba;
import motel.model.Racun;
import motel.model.Soba;
import motel.utils.Inputer;
import motel.utils.Printer;

public class MeniRecepcioner {

	private OsobaKontroler osobaKontroler;
	private IznajmljivanjeKontroler iznajmljivanjeKontroler;
	private RacunKontroler racunKontroler;
	private SobaKontroler sobaKontroler;
	
	public MeniRecepcioner(OsobaKontroler osobaKontroler,
						   SobaKontroler sobaKontroler, 
						   IznajmljivanjeKontroler iznajmljivanjeKontroler,
						   RacunKontroler racunKontroler) {
		this.osobaKontroler = osobaKontroler;
		this.iznajmljivanjeKontroler = iznajmljivanjeKontroler;
		this.racunKontroler = racunKontroler;
		this.sobaKontroler = sobaKontroler;
	}

	public void pokreni() {
		Loop:while(true) {
			System.out.println("\n~~~~~Meni~~~~~\n");
			System.out.println(" 1. Administracija iznajmljivanja");
			System.out.println(" 2. Administracija racuna");
			System.out.println(" 3. Izmeni profil");
			System.out.println(" 4. Izloguj se");
			System.out.println(" 0. Izlaz ");
			System.out.print("\n Izaberite opciju (0-4): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{
					System.exit(0);
				}
				case 1:{
					pokreniPodMeniIznjamljivanje();
					break;
				}
				case 2:{
					pokreniPodMeniRacun();
					break;
				}
				case 3:{
					break;
				}
				case 4:{
					break Loop;

				}
			}
		}

	}
	
	public void pokreniPodMeniIznjamljivanje() {
		Loop:while(true) {
			System.out.println("\n~~~~~Adminstracija iznajmljivanja~~~~~\n");
			System.out.println(" 1. Pregled iznajmljivanja");
			System.out.println(" 2. Dodavanje iznajmljivanja");
			System.out.println(" 3. Izmena iznajmljivanja");
			System.out.println(" 4. Brisanje iznjamljivanja");
			System.out.println(" 0. Nazad u glavni meni ");
			System.out.print("\n Izaberite opciju (0-4): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{				
					break Loop;
				}
				case 1:{
					
					System.out.println("\n\t~Iznjamljivanja~\n");
					Printer.printIznajmljivanjeZaglavlje();
					iznajmljivanjeKontroler.getIznajmljivanja().stream().forEach(i -> Printer.printIznajmljivanje(i));
					System.out.println(" 1. Sortiranje iznajmljivanja");
					System.out.println(" 2. Pretraga iznajmljivanja");
					System.out.println(" 0. Nazad");
					System.out.print("\n Izaberite opciju (0-2): ");
					int unos = Inputer.getInt();
					switch(unos) {
						case 1:{
							System.out.println(" 1. Sortiranje iznajmljivanja po broju sobe");
							System.out.println(" 2. Sortiranje iznajmljivanja po tipu sobe");
							System.out.println(" 3. Sortiranje iznajmljivanja po datumu pocetka");
							System.out.println(" 4. Sortiranje iznajmljivanja po datumu zavrsetka");
							System.out.println(" 5. Sortiranje iznajmljivanja po broju sobe i datumu pocetka");
							System.out.println(" 6. Sortiranje iznajmljivanja po broju sobe i datumu zavrsetka");
							System.out.println(" 0. Nazad");
							System.out.print("\n Izaberite opciju (0-3): ");
							int sort = Inputer.getInt();
							if(sort == 1) {
								System.out.println("\n\t~Sortirana znjamljivanja po broju sobe~\n");
								Printer.printIznajmljivanjeZaglavlje();
								iznajmljivanjeKontroler.getSortiranaIznajmljivanjePoBrojuSobe().stream().forEach(i -> Printer.printIznajmljivanje(i));
							}else if(sort ==2) {
								System.out.println("\n\t~Sortirana znjamljivanja po tipu sobe~\n");
								Printer.printIznajmljivanjeZaglavlje();
								iznajmljivanjeKontroler.getSortiranaIznajmljivanjePoTipuSobe().stream().forEach(i -> Printer.printIznajmljivanje(i));
							}else if(sort == 3) {
								System.out.println("\n\t~Sortirana znjamljivanja po datumu pocetka~\n");
								Printer.printIznajmljivanjeZaglavlje();
								iznajmljivanjeKontroler.getSortiranaIznajmljivanjePoDatumPocetka().stream().forEach(i -> Printer.printIznajmljivanje(i));
							}else if(sort == 4) {
								System.out.println("\n\t~Sortirana znjamljivanja po datumu zavrstka~\n");
								Printer.printIznajmljivanjeZaglavlje();
								iznajmljivanjeKontroler.getSortiranaIznajmljivanjePoDatumZavrsetka().stream().forEach(i -> Printer.printIznajmljivanje(i));
							}else if(sort == 5) {
								System.out.println("\n\t~Sortirana znjamljivanja po broju sobe i datumu pocetka~\n");
								Printer.printIznajmljivanjeZaglavlje();
								iznajmljivanjeKontroler.getSortiranaIznajmljivanjePoBrojuSobeIDatumPocetka().stream().forEach(i -> Printer.printIznajmljivanje(i));
							}else if(sort == 6) {
								System.out.println("\n\t~Sortirana znjamljivanja po broju sobe i datumu zavrsetka~\n");
								Printer.printIznajmljivanjeZaglavlje();
								iznajmljivanjeKontroler.getSortiranaIznajmljivanjePoBrojSobeIDatumZavrsetka().stream().forEach(i -> Printer.printIznajmljivanje(i));
							}
							
							
						}
						case 2:{
							System.out.println("\n\t~Pretraga iznajmljivanja~\n");
							System.out.print(" Uneseti broj sobe:");
							String brojSobe = Inputer.getEmptyOrString();
							
							System.out.print(" Datum pocetka(dd.MM.yyyy. HH.mm): ");
							String datumPocetka = Inputer.getEmptyOrString();

							
							System.out.print(" Datum zavrsetka(dd.MM.yyyy. HH.mm): ");
							String datumZavrsetka = Inputer.getEmptyOrString();

							System.out.print(" Broj lične karte: ");
							String brojLicneKarte= Inputer.getEmptyOrString();
							
							List<Iznajmljivanje> iznajmljivanjaPretrage =iznajmljivanjeKontroler.pretraga(brojSobe, datumPocetka, datumZavrsetka, brojLicneKarte);
							System.out.println("\n\t~Pretrazena iznjamljivanja~\n");
							Printer.printIznajmljivanjeZaglavlje();
							iznajmljivanjaPretrage.stream().forEach(i -> Printer.printIznajmljivanje(i));
						}
					}
					break;
				}
				case 2:{
					
					System.out.println("\n\t~Dodavanje iznajmljivanja~\n");
					
					Printer.printSobaZaglavlje();
					sobaKontroler.getSobe().stream().forEach(s -> Printer.printSoba(s));
					System.out.print("\n Uneseti broj sobe:");
					String brojSobe = Inputer.getString();
					Soba soba = sobaKontroler.getSoba(brojSobe);
					if(soba ==null ) {
						System.out.println("Soba sa brojem '"+brojSobe+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Datum pocetka(dd.MM.yyyy. HH.mm): ");
					LocalDateTime datumPocetka = Inputer.getDateTime();
					if(datumPocetka.isBefore(LocalDateTime.now()) ) {
						System.out.println("Datum pocetka ne moze biti pre danasnjeg datuma!");
						continue;
					}
					
					System.out.print(" Datum zavrsetka(dd.MM.yyyy. HH.mm): ");
					LocalDateTime datumZavrsetka = Inputer.getDateTime();
					if(datumPocetka.isBefore(LocalDateTime.now()) ) {
						System.out.println("Datum zavrsetka ne moze biti pre danasnjeg datuma!");
						continue;
					}
					
					if(datumPocetka.isAfter(datumZavrsetka)) {
						System.out.println("Datum pocetka ne moze biti posle datuma zavrsetka!");
						continue;
					}

					List<Osoba> gosti = new ArrayList<>();
					while(true) {
						System.out.println( "~Gost~");
						
						System.out.print(" Ime: ");
						String ime = Inputer.getString();
						
						System.out.print(" Prezime: ");
						String prezime = Inputer.getString();
						
						System.out.print(" Broj lične karte: ");
						String brojLicneKarte= Inputer.getString();
						
						Osoba osoba = new Osoba(ime, prezime, brojLicneKarte);
						gosti.add(osoba);
						
						System.out.print("Da li zelite da unosite jos gostiju(da/ne):");
						String josGostiju= Inputer.getString();
						
						if(josGostiju.equalsIgnoreCase("da")) {
							continue;
						}
						break;
						
						
					}
					
					Iznajmljivanje iznajmljivanje = new Iznajmljivanje(gosti, soba, datumPocetka, datumZavrsetka);
					boolean uspensnoDodavanje = iznajmljivanjeKontroler.dodajIznajmljivanje(iznajmljivanje);
					if(!uspensnoDodavanje) {
						System.out.println("Neuspesno dodavanje iznajmljivanja!");
					}
					
					break;
				}

				case 3:{
					System.out.println("\n\t~Izmena iznajmljivanja~\n");
					System.out.print("   |");
					Printer.printIznajmljivanjeZaglavlje();
					for(int i = 0; i< iznajmljivanjeKontroler.getIznajmljivanja().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printIznajmljivanje(iznajmljivanjeKontroler.getIznajmljivanje(i));
					}
					System.out.print("\n Uneseti broj iznjamljivanja za izmenu:");
					int redniBroj =Inputer.getInt();
					Iznajmljivanje iznajmljivanje = iznajmljivanjeKontroler.getIznajmljivanje(redniBroj -1 );
					if(iznajmljivanje ==null ) {
						System.out.println("Redni broj '"+redniBroj+"' ne postoji!");
						continue;
					}
					
					Printer.printSobaZaglavlje();
					sobaKontroler.getSobe().stream().forEach(s -> Printer.printSoba(s));
					System.out.print("\n Uneseti broj sobe("+iznajmljivanje.getSoba().getBroj()+"):");
					String brojSobe =Inputer.getString();
					Soba soba = sobaKontroler.getSoba(brojSobe);
					if(soba == null) {
						System.out.println("Soba sa brojem '"+brojSobe+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Datum pocetka(dd.MM.yyyy. HH.mm): ");
					LocalDateTime datumPocetka = Inputer.getDateTime();
					if(datumPocetka.isBefore(LocalDateTime.now()) ) {
						System.out.println("Datum pocetka ne moze biti pre danasnjeg datuma!");
						continue;
					}
					
					System.out.print(" Datum zavrsetka(dd.MM.yyyy. HH.mm): ");
					LocalDateTime datumZavrsetka = Inputer.getDateTime();
					if(datumPocetka.isBefore(LocalDateTime.now()) ) {
						System.out.println("Datum zavrsetka ne moze biti pre danasnjeg datuma!");
						continue;
					}
					
					if(datumPocetka.isAfter(datumZavrsetka)) {
						System.out.println("Datum pocetka ne moze biti posle datuma zavrsetka!");
						continue;
					}
										
					List<Osoba> gosti = new ArrayList<>();
					int index = 0;
					while(true) {
						System.out.println( "~Gost~");
						
						String ime=null;
						String prezime = null;
						String brojLicneKarte=null;
						if(index >iznajmljivanje.getGosti().size()-1) {
							System.out.print(" Ime: ");
							ime = Inputer.getString();
							
							System.out.print(" Prezime: ");
							prezime = Inputer.getString();
							
							System.out.print(" Broj lične karte: ");
							brojLicneKarte= Inputer.getString();
						}else {
							System.out.print(" Ime(" + iznajmljivanje.getGosti().get(index).getIme()+ "): ");
							ime = Inputer.getString();
							
							System.out.print(" Prezime("+ iznajmljivanje.getGosti().get(index).getPrezime()+"): ");
							prezime = Inputer.getString();
							
							System.out.print(" Broj lične karte("+iznajmljivanje.getGosti().get(index).getBrojLicneKarte()+"): ");
							brojLicneKarte= Inputer.getString();
						}
	
						Osoba osoba = new Osoba(ime, prezime, brojLicneKarte);
						gosti.add(osoba);
						
						System.out.print("Da li zelite da unosite jos gostiju(da/ne):");
						String josGostiju= Inputer.getString();
						index++;
						if(josGostiju.equalsIgnoreCase("da")) {
							continue;
						}
						break;
						
						
					}
					
					Iznajmljivanje novoIznajmljivanje = new Iznajmljivanje(gosti, soba, datumPocetka, datumZavrsetka);
					boolean uspesnaIzmena = iznajmljivanjeKontroler.izmeniIznajmljivanje(iznajmljivanje, novoIznajmljivanje);
					if(!uspesnaIzmena) {
						System.out.println("Neuspesna izmena iznajmljivanja!");
					}
					break;
				}
				
				case 4:{
					System.out.println("\n\t~Brisanje iznajmljivanja~\n");
					System.out.print("   |");
					Printer.printIznajmljivanjeZaglavlje();
					for(int i = 0; i< iznajmljivanjeKontroler.getIznajmljivanja().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printIznajmljivanje(iznajmljivanjeKontroler.getIznajmljivanje(i));
					}
					System.out.print("\n Uneseti broj iznjamljivanja za brisanje:");
					int redniBroj =Inputer.getInt();
					boolean uspesnoBrisanje = iznajmljivanjeKontroler.obrisiIznajmljivanje(redniBroj-1);
					if(!uspesnoBrisanje) {
						System.out.println("Brisanje neuspešno! RedniBroj'" +redniBroj+"' ne postoji!");
					}
					break;
				}
			}
		}
	}
	
	public void pokreniPodMeniRacun() {
		Loop:while(true) {
			System.out.println("\n~~~~~Adminstracija racuna~~~~~\n");
			System.out.println(" 1. Pregled racuna");
			System.out.println(" 2. Dodavanje racuna");
			System.out.println(" 0. Nazad u glavni meni ");
			System.out.print("\n Izaberite opciju (0-2): ");
			int opcija = Inputer.getInt();
			
			switch (opcija) {
				case 0:{				
					break Loop;
				}
				case 1:{
					
					System.out.println("\n\t~Racuni~\n");
					Printer.printRacunZaglavlje();
					racunKontroler.getRacuni().stream().forEach(r -> Printer.printRacun(r));
					System.out.println(" 1. Sortiranje tip soba");
					System.out.println(" 2. Pretraga tip soba");
					System.out.println(" 0. Nazad");
					System.out.print("\n Izaberite opciju (0-2): ");
					int unos = Inputer.getInt();
					switch(unos) {
						case 1:{
							System.out.println(" 1. Sortiranje racuna po datumu");
							System.out.println(" 2. Sortiranje racuna po tipu sobe");
							System.out.println(" 3. Sortiranje racuna po broju sobe");
							System.out.println(" 4. Sortiranje racuna po imenu i prezimenu uplatioca");
							System.out.println(" 5. Sortiranje racuna po ukupnoj ceni");
							System.out.println(" 0. Nazad");
							System.out.print("\n Izaberite opciju (0-3): ");
							int sort = Inputer.getInt();
							if(sort == 1) {
								System.out.println("\n\t~Sortirani racuni po datumu~\n");
								Printer.printRacunZaglavlje();
								racunKontroler.getSortiraniRacuniPoDatumu().stream().forEach(r -> Printer.printRacun(r));
							}else if(sort ==2) {
								System.out.println("\n\t~Sortirani racuni po tipu sobe~\n");
								Printer.printRacunZaglavlje();
								racunKontroler.getSortiraniRacuniPoTipuSobe().stream().forEach(r -> Printer.printRacun(r));
							}else if(sort == 3) {
								System.out.println("\n\t~Sortirani racuni po broju sobe~\n");
								Printer.printRacunZaglavlje();
								racunKontroler.getSortiraniRacuniPoBrojuSobe().stream().forEach(r -> Printer.printRacun(r));
							}else if(sort == 4) {
								System.out.println("\n\t~Sortirani racuni po imenu i prezimenu uplatioca~\n");
								Printer.printRacunZaglavlje();
								racunKontroler.getSortiraniRacuniPoImeIPrezime().stream().forEach(r -> Printer.printRacun(r));
							}else if(sort == 5) {
								System.out.println("\n\t~Sortirani racuni po ukupnoj ceni~\n");
								Printer.printRacunZaglavlje();
								racunKontroler.getSortiraniRacuniPoCeni().stream().forEach(r -> Printer.printRacun(r));
							}
							
							
						}
						case 2:{
							System.out.println("\n\t~Pretraga racuna~\n");
							System.out.print(" Datum izdavanja od: ");
							String datumOd = Inputer.getEmptyOrString();
							
							System.out.print(" Datum izdavanja do: ");
							String datumDo = Inputer.getEmptyOrString();
							
							System.out.print(" Ukupna cena od: ");
							String ukupnaCenaOd = Inputer.getEmptyOrString();
							
							System.out.print(" Ukupna cena do: ");
							String ukupnaCenaDo = Inputer.getEmptyOrString();
														
							System.out.print(" Broj sobe: ");
							String brojSobe = Inputer.getEmptyOrString();
							
							System.out.print(" Broj kreveta: ");
							String brojKreveta = Inputer.getEmptyOrString();
							
							System.out.print(" Ime platioca: ");
							String ime = Inputer.getEmptyOrString();
							
							System.out.print(" Prezime platioca: ");
							String prezime = Inputer.getEmptyOrString();
							
							List<Racun> pretrazeniRacuni = racunKontroler.pretraga(datumOd, datumDo, ukupnaCenaOd, ukupnaCenaDo, brojSobe, brojKreveta, ime , prezime);
							System.out.println("\n\t~Pretrazeni racuni~\n");
							Printer.printRacunZaglavlje();
							pretrazeniRacuni.stream().forEach(r -> Printer.printRacun(r));
						}
					}
					break;
				}
				case 2:{
					System.out.print("   |");
					Printer.printIznajmljivanjeZaglavlje();
					for(int i = 0; i< iznajmljivanjeKontroler.getIznajmljivanja().size();i++) {
						System.out.print((i+1)+". |" );
						Printer.printIznajmljivanje(iznajmljivanjeKontroler.getIznajmljivanje(i));
					}
					System.out.print("\n Uneseti broj iznjamljivanja za izmenu:");
					int redniBroj =Inputer.getInt();
					Iznajmljivanje iznajmljivanje = iznajmljivanjeKontroler.getIznajmljivanje(redniBroj -1 );
					if(iznajmljivanje ==null ) {
						System.out.println("Redni broj '"+redniBroj+"' ne postoji!");
						continue;
					}
					
					System.out.print(" Ime platioca: ");
					String ime = Inputer.getString();
					
					System.out.print(" Prezime platioca: ");
					String prezime = Inputer.getString();
					
					System.out.print(" Broj lične karte platioca: ");
					String brojLicneKarte= Inputer.getString();
					
					Osoba osoba = new Osoba(ime, prezime, brojLicneKarte);
					
					double ukupnaCena = racunKontroler.izracunajUkupnuCenu(iznajmljivanje);
					Racun racun = new Racun(ukupnaCena, iznajmljivanje, osoba);
					racunKontroler.dodajRacun(racun);
					osobaKontroler.dodajRacunRecepcioneru(racun);
				}
			}
		}
	}
}
