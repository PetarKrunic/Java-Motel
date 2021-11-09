package motel.kontroler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motel.dao.OsobaDAO;
import motel.model.Korisnik;
import motel.model.Menadzer;
import motel.model.Osoba;
import motel.model.Racun;
import motel.model.Recepcioner;
import motel.model.StavkaCenovnika;

public class OsobaKontroler {

	private OsobaDAO osobaDAO;
	private Korisnik ulogovaniKorisnik;
	
	public OsobaKontroler(OsobaDAO osobaDAO) {
		this.osobaDAO = osobaDAO;
		
	}
	
	public Korisnik logovanje(String korisnickoIme, String lozinka) {
		for(Korisnik korisnik : osobaDAO.getKorisnici()) {
			if(korisnik.getKorisnickoIme().equals(korisnickoIme) 
			&& korisnik.getLozinka().equals(lozinka) ) {
				ulogovaniKorisnik= korisnik;
				return korisnik;
			}
			
		}
		return null;
	}
	
	public Menadzer getMenadzer(String korisnickoIme) {
		for(Menadzer m : osobaDAO.getMenadzeri()) {
			if(m.getKorisnickoIme().equals(korisnickoIme)) {
				return m;
			}
		}
		return null;
	}
	
	public Recepcioner getRecepcioner(String korisnickoIme) {
		for(Recepcioner r : osobaDAO.getRecepcionari()) {
			if(r.getKorisnickoIme().equals(korisnickoIme)) {
				return r;
			}
		}
		return null;
	}
	
	public List<Menadzer> getMenadzeri(){
		return osobaDAO.getMenadzeri();
	}
	
	public List<Recepcioner> getRecepcionari(){
		return osobaDAO.getRecepcionari();
	}
	
	public boolean dodajOsobu(Osoba osoba) { 
		if( postojiOsoba(osoba.getBrojLicneKarte())) {
			return false;
		}
		
		if(osoba instanceof Korisnik) {
			Korisnik korisnik = (Korisnik)osoba;
			if(postojiKorisnik(korisnik.getKorisnickoIme())) {
				return false;
			}
		}
		osobaDAO.dodajOsobu(osoba);
		return true;

	}

	public boolean obrisiOsobu(String korisnickoIme) {
		if(!postojiKorisnik(korisnickoIme)) {
			return false;
		}
		osobaDAO.obrisiKorisnika(korisnickoIme);
		return true;
		
	}
	
	public boolean izmeniKorisnika(Korisnik korisnik, Korisnik noviKorisnik) {
		if(!korisnik.getKorisnickoIme().equals(noviKorisnik.getKorisnickoIme()) &&
			postojiKorisnik(noviKorisnik.getKorisnickoIme())) {
			return false;
		}
		korisnik.setIme(noviKorisnik.getIme());
		korisnik.setPrezime(noviKorisnik.getPrezime());
		korisnik.setBrojLicneKarte(noviKorisnik.getBrojLicneKarte());
		korisnik.setKorisnickoIme(noviKorisnik.getKorisnickoIme());
		korisnik.setLozinka(noviKorisnik.getLozinka());
		osobaDAO.sacuvaj();
		return true;
		
	}
	
	public boolean postojiOsoba(String brojLicneKarte) {
		return osobaDAO.getOsobe().stream()
		.anyMatch(o -> o.getBrojLicneKarte().equals(brojLicneKarte));
	}
	
	public boolean postojiKorisnik(String korisnickoIme) {
		return osobaDAO.getKorisnici().stream()
				.anyMatch(k -> k.getKorisnickoIme().equals(korisnickoIme));
	}
	
	public Korisnik getUlogovaniKorisnik() {
		return ulogovaniKorisnik;
	}
	
	public void dodajRacunRecepcioneru(Racun racun) {
		if(!(getUlogovaniKorisnik() instanceof Recepcioner)) {
			return;
		}
		Recepcioner recepcioner =(Recepcioner)getUlogovaniKorisnik();
		recepcioner.getIzdatiRacuni().add(racun);
		osobaDAO.sacuvaj();
	}
	
	public void dodajStavkuCenovnikaMenadzeru(StavkaCenovnika stavka) {
		if(!(getUlogovaniKorisnik() instanceof Menadzer)) {
			return;
		}
		Menadzer menadzer =(Menadzer)getUlogovaniKorisnik();
		menadzer.getDodateStavke().add(stavka);
		osobaDAO.sacuvaj();
	}
	
	
	public List<Korisnik>  getSortiraniKorisniciPoImenuIPrezimenu(){
		List<Korisnik> sortiraniKorisnici = new ArrayList<>(this.osobaDAO.getKorisnici());
		Collections.sort(sortiraniKorisnici, new SortByImeAndPrezime());	
		return sortiraniKorisnici;
	}
	
	public List<Korisnik> getSortiraniKorisniciPoKorisnickomImenu(){
		List<Korisnik> sortiraniKorisnici = new ArrayList<>(this.osobaDAO.getKorisnici());
		Collections.sort(sortiraniKorisnici, new SortByKorisnickoIme());	
		return sortiraniKorisnici;			
	}
	
	
	public class SortByImeAndPrezime implements Comparator<Korisnik>{

		@Override
		public int compare(Korisnik k1, Korisnik k2) {

			int comparator = k1.getIme().compareTo(k2.getIme());
			if(comparator == 0) {
				comparator = k1.getPrezime().compareTo(k2.getPrezime());
			}
			return comparator;
		}
		
	}
	
	public class SortByKorisnickoIme implements Comparator<Korisnik>{

		@Override
		public int compare(Korisnik k1, Korisnik k2) {

			return k1.getKorisnickoIme().compareTo(k2.getKorisnickoIme());
		}
		
	}

	

	
	
}
