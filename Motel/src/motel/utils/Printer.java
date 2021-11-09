package motel.utils;

import java.time.format.DateTimeFormatter;

import motel.model.Iznajmljivanje;
import motel.model.Korisnik;
import motel.model.Osoba;
import motel.model.Racun;
import motel.model.Soba;
import motel.model.StavkaCenovnika;
import motel.model.TipSobe;

public class Printer {

	public static DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH.mm");
	public static void printKorisnikZaglavlje() {
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s", "Ime", "|", "Prezime", "|", "Broj licne karte","|", "Korisnicko ime","|", "Lozinka","|"));
	    System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------"));
	}
	public static void printKorisnik(Korisnik korisnik) {
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s", korisnik.getIme(), "|", korisnik.getPrezime(), "|", korisnik.getBrojLicneKarte(),"|",korisnik.getKorisnickoIme(), "|",korisnik.getLozinka(), "|"));
	}
	
	public static void printTipSobeZaglavlje() {
	    System.out.println(String.format("%20s %1s %20s %1s", "Naziv", "|", "Broj kreveta", "|"));
	    System.out.println(String.format("%s", "---------------------------------------------"));
	}
	public static void printTipSobe(TipSobe tipSobe) {
	    System.out.println(String.format("%20s %1s %20s %1s", tipSobe.getNaziv(), "|", tipSobe.getBrojKreveta(), "|"));
	}
	
	public static void printSobaZaglavlje() {
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s", "Broj sobe", "|", "Naziv tipa sobe", "|", "Broj kreveta","|", "TV","|", "MiniBar","|"));
	    System.out.println(String.format("%s", "------------------------------------------------------------------------------------------------------------------"));
	}
	public static void printSoba(Soba soba) {
		String tv = soba.isTv() ? "da" : "ne";
		String miniBar= soba.isMiniBar() ? "da" : "ne";
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s", soba.getBroj(), "|", soba.getTipSobe().getNaziv(), "|", soba.getTipSobe().getBrojKreveta(),"|",tv, "|",miniBar, "|"));
	}
	
	public static void printCenovnikZaglavlje() {
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s %25s %1s", "Naziv tipa sobe", "|","Broj kreveta","|", "Datum kreiranja", "|", "Dnevni boravak (din)","|", "Noćenje (din)","|", "Vikend poskupljenje (%)","|"));
	    System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------------"));
	}
	public static void printCenovnik(StavkaCenovnika stavkaCenovnika) {

	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s %25s %1s", stavkaCenovnika.getTipSobe().getNaziv(), "|", stavkaCenovnika.getTipSobe().getBrojKreveta(), "|", stavkaCenovnika.getDatumKreiranja().format(dateTimeFormatter),"|",stavkaCenovnika.getDnevniBoravak(), "|",stavkaCenovnika.getNocenje(), "|",stavkaCenovnika.getVikendPoskupljenje(),"|"));
	}
	
	public static void printIznajmljivanjeZaglavlje() {
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %40s %1s ", "Broj sobe", "|","Datum pocetka","|", "Datum zavrsetka", "|", "Gosti","|"));
	    System.out.println(String.format("%s", "----------------------------------------------------------------------------------------------------------------------------------------------"));
	}
	
	public static void printIznajmljivanje(Iznajmljivanje iznajmljivanje) {
		String gosti="";
		for(Osoba gost : iznajmljivanje.getGosti()) {
			gosti += gost.getIme() + " "+ gost.getPrezime() +", "; 
		}
		gosti = gosti.substring(0, gosti.length()-2);
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %40s %1s",iznajmljivanje.getSoba().getBroj(), "|", iznajmljivanje.getDatumPocetka().format(dateTimeFormatter), "|", iznajmljivanje.getDatumZavrsetka().format(dateTimeFormatter),"|",gosti, "|"));
	}
	
	public static void printRacunZaglavlje() {
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s %30s %1s", "Ukupna cena","|","Datum izdavanja","|","Broj sobe","|","Datum pocetka","|", "Datum zavrsetka", "|", "Osoba koja placa","|"));
	    System.out.println(String.format("%s", "---------------------------------------------------------------------------------------------------------------------------------------------------"));
	}
	
	public static void printRacun(Racun racun) {
		String osoba = racun.getOsoba().getIme() + " " + racun.getOsoba().getPrezime() + ", " + racun.getOsoba().getBrojLicneKarte();
	    System.out.println(String.format("%20s %1s %20s %1s %20s %1s %20s %1s %20s %1s %30s %1s",racun.getUkupnaCena(),"|",racun.getDatumIzdavanja().format(dateTimeFormatter),"|",racun.getIznajmljivanje().getSoba().getBroj(),"|", racun.getIznajmljivanje().getDatumPocetka().format(dateTimeFormatter), "|", racun.getIznajmljivanje().getDatumZavrsetka().format(dateTimeFormatter),"|",osoba, "|"));
	}
	
	
	

}
