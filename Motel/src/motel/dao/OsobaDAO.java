package motel.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import motel.model.Korisnik;
import motel.model.Menadzer;
import motel.model.Osoba;
import motel.model.Racun;
import motel.model.Recepcioner;
import motel.model.StavkaCenovnika;

public class OsobaDAO {

	private final String datoteka = "osobe.txt";
	private List<Osoba> osobe;
	
	private CenovnikDAO cenovnikaDAO;
	private RacunDAO racunDAO;
	
	public OsobaDAO(CenovnikDAO cenovnikaDAO, RacunDAO racunDAO) {
		this.cenovnikaDAO = cenovnikaDAO;
		this.racunDAO = racunDAO;
		osobe = ucitaj();
		
		boolean postojiAdmin = osobe.stream()
				.filter(o -> o instanceof Korisnik)
				.anyMatch(k -> ((Korisnik)k).getKorisnickoIme().equals("admin")); 
		if(!postojiAdmin){
			Korisnik admin = new Korisnik("Adminko", "Adminić", "cuj admin da ima licnu kartu", "admin", "admin");
			osobe.add(admin);
		}
		
	}
	
	public List<Osoba> getOsobe() {
		return osobe.stream().filter(o -> !o.isObrisan()).collect(Collectors.toList());
	}
	
	public List<Korisnik> getKorisnici() {
		return getOsobe().stream()
				.filter( o -> o instanceof Korisnik)
				.map(o -> (Korisnik)o)
				.collect(Collectors.toList());
	}
	
	public List<Menadzer> getMenadzeri() {
		return getOsobe().stream()
				.filter( o -> o instanceof Menadzer)
				.map(o -> (Menadzer)o)
				.collect(Collectors.toList());
	}

	public List<Recepcioner> getRecepcionari() {
		return getOsobe().stream()
				.filter( o -> o instanceof Recepcioner)
				.map(o -> (Recepcioner)o)
				.collect(Collectors.toList());
	}
	
	public void dodajOsobu(Osoba osoba) {
		this.osobe.add(osoba);
		sacuvaj();
	}
	
	public void sacuvaj() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(datoteka));
			for(Osoba osoba : osobe) {
				writer.println(osoba.ispis());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(writer !=null) {
				writer.close();
			}
			
		}
		
	}
	
	private List<Osoba> ucitaj() {
		Scanner scanner = null;
		List<Osoba> osobe = new ArrayList<>();
		try {
			File file = new File(datoteka);
			file.createNewFile();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
								
				String tip = tokenizer.nextToken().trim();
				boolean obrisan = Boolean.valueOf( tokenizer.nextToken().trim() );
				String ime = tokenizer.nextToken().trim();
				String prezime = tokenizer.nextToken().trim();
				String brojLicneKarte = tokenizer.nextToken().trim();
				if(!tip.equals("Osoba")) {
					String korisnickoIme = tokenizer.nextToken().trim();
					String lozinka = tokenizer.nextToken().trim();
					if(tip.equals("Menadzer")) {
						List<StavkaCenovnika> stavke = new ArrayList<>();
						if(tokenizer.hasMoreTokens()) {
							tokenizer = new StringTokenizer(tokenizer.nextToken(), ";");
							while(tokenizer.hasMoreTokens()) {
								LocalDateTime datumKreiranja = LocalDateTime.parse(tokenizer.nextToken().trim());
								StavkaCenovnika stavkaCenovnika = cenovnikaDAO.getStavkaByDatum(datumKreiranja);
								if(stavkaCenovnika != null) {
									stavke.add(stavkaCenovnika);
								}
							}
						}
						Menadzer menadzer = new Menadzer(ime, prezime, brojLicneKarte, obrisan,korisnickoIme, lozinka,stavke);
						osobe.add(menadzer);
						continue;
					}else if(tip.equals("Recepcioner")) {
						List<Racun> izdatiRacuni = new ArrayList<>();
						if(tokenizer.hasMoreTokens()) {
							tokenizer = new StringTokenizer(tokenizer.nextToken(), ";");
							while(tokenizer.hasMoreTokens()) {
								LocalDateTime datumIzdavanja = LocalDateTime.parse(tokenizer.nextToken().trim());
						
								Racun racun = racunDAO.getRacun(datumIzdavanja);
								izdatiRacuni.add(racun);
							}
						}
						Recepcioner recepcioner = new Recepcioner(ime, prezime, brojLicneKarte,obrisan, korisnickoIme, lozinka, izdatiRacuni);
						osobe.add(recepcioner);
						continue;
					}else {
						Korisnik korisnik = new Korisnik(ime, prezime, brojLicneKarte,obrisan, korisnickoIme, lozinka);
						osobe.add(korisnik);
						continue;
					}	
				}
				Osoba osoba = new Osoba(ime, prezime, brojLicneKarte,obrisan);
				osobe.add(osoba);						
			}
		}catch (Exception e) {
			
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		return osobe;
	}

	public void obrisiKorisnika(String korisnickoIme) {
		for(Korisnik korisnik : getKorisnici()) {
			if(korisnik.getKorisnickoIme().equals(korisnickoIme)) {
				korisnik.setObrisan(true);
				sacuvaj();
				break;
			}
		}
		
	}
}
