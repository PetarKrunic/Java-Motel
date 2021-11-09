package motel.kontroler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motel.dao.IznajmljivanjeDAO;
import motel.model.Iznajmljivanje;
import motel.model.Osoba;
import motel.utils.Printer;

public class IznajmljivanjeKontroler {

	private IznajmljivanjeDAO iznajmljivanjeDAO;
	
	public IznajmljivanjeKontroler(IznajmljivanjeDAO iznajmljivanjeDAO) {
		this.iznajmljivanjeDAO = iznajmljivanjeDAO;
	}

	public Iznajmljivanje getIznajmljivanje(int index) {

		if( index < 0 || index > iznajmljivanjeDAO.getIznajmljivanja().size()-1) {
			return null;
		}
		
		return iznajmljivanjeDAO.getIznajmljivanja().get(index);
	}
	
	public List<Iznajmljivanje> getIznajmljivanja(){
		return iznajmljivanjeDAO.getIznajmljivanja();			
	}
	
	public boolean postojiIznajmljivanje(Iznajmljivanje iznajmljivanje) {
		for(Iznajmljivanje i : getIznajmljivanja()) {
			if(i.getSoba().getBroj().equals(iznajmljivanje.getSoba().getBroj())) {
				if(iznajmljivanje.getDatumZavrsetka().compareTo(i.getDatumPocetka  ()) >= 0 && 
				   iznajmljivanje.getDatumPocetka  ().compareTo(i.getDatumZavrsetka()) <= 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean dodajIznajmljivanje(Iznajmljivanje iznajmljivanje) { 
		if(postojiIznajmljivanje(iznajmljivanje)) {
			return false;
		}
		iznajmljivanjeDAO.dodajIznajmljivanje(iznajmljivanje);
		return true;

	}

	public boolean obrisiIznajmljivanje(int index) {
		if( index < 0 || index > iznajmljivanjeDAO.getIznajmljivanja().size()-1) {
			return false;
		}
		
		iznajmljivanjeDAO.obrisiIznamljivanje(index);
		return true;
		
	}
	
	public boolean izmeniIznajmljivanje(Iznajmljivanje iznajmljivanje, Iznajmljivanje novoIznajmljivanje) {
		String brojSobe = iznajmljivanje.getSoba().getBroj();
		String noviBrojSobe = novoIznajmljivanje.getSoba().getBroj();
		
		LocalDateTime datumPocetka = iznajmljivanje.getDatumPocetka();
		LocalDateTime noviDatumPocetka = novoIznajmljivanje.getDatumPocetka();

		LocalDateTime datumZavrsetka = iznajmljivanje.getDatumZavrsetka();
		LocalDateTime noviDatumZavrsetka = novoIznajmljivanje.getDatumZavrsetka();
		if( (!brojSobe.equals(noviBrojSobe) ||
			 !datumPocetka.isEqual(noviDatumPocetka) ||
			 !datumZavrsetka.isEqual(noviDatumZavrsetka)) &&
			postojiIznajmljivanje(novoIznajmljivanje)) {
			return false;
		}
		iznajmljivanjeDAO.izmeniIznajmljivanje(iznajmljivanje, novoIznajmljivanje);
		return true;
		
	}
	
	public List<Iznajmljivanje>  getSortiranaIznajmljivanjePoBrojuSobe(){
		List<Iznajmljivanje> sortiranaIznajmljivanja = new ArrayList<>(this.iznajmljivanjeDAO.getIznajmljivanja());
		Collections.sort(sortiranaIznajmljivanja, new SortByBrojSobe());	
		return sortiranaIznajmljivanja;
	}
	
	public List<Iznajmljivanje> getSortiranaIznajmljivanjePoTipuSobe(){
		List<Iznajmljivanje> sortiranaIznajmljivanja = new ArrayList<>(this.iznajmljivanjeDAO.getIznajmljivanja());
		Collections.sort(sortiranaIznajmljivanja, new SortByTipSobe());	
		return sortiranaIznajmljivanja;			
	}
	
	public List<Iznajmljivanje> getSortiranaIznajmljivanjePoDatumPocetka(){
		List<Iznajmljivanje> sortiranaIznajmljivanja = new ArrayList<>(this.iznajmljivanjeDAO.getIznajmljivanja());
		Collections.sort(sortiranaIznajmljivanja, new SortByDatumPocetka());	
		return sortiranaIznajmljivanja;			
	}
	
	public List<Iznajmljivanje> getSortiranaIznajmljivanjePoDatumZavrsetka(){
		List<Iznajmljivanje> sortiranaIznajmljivanja = new ArrayList<>(this.iznajmljivanjeDAO.getIznajmljivanja());
		Collections.sort(sortiranaIznajmljivanja, new SortByDatumZavrsetka());	
		return sortiranaIznajmljivanja;			
	}
	
	public List<Iznajmljivanje> getSortiranaIznajmljivanjePoBrojuSobeIDatumPocetka(){
		List<Iznajmljivanje> sortiranaIznajmljivanja = new ArrayList<>(this.iznajmljivanjeDAO.getIznajmljivanja());
		Collections.sort(sortiranaIznajmljivanja, new SortByBrojSobeAndDatumPocetka());	
		return sortiranaIznajmljivanja;			
	}
	
	public List<Iznajmljivanje> getSortiranaIznajmljivanjePoBrojSobeIDatumZavrsetka(){
		List<Iznajmljivanje> sortiranaIznajmljivanja = new ArrayList<>(this.iznajmljivanjeDAO.getIznajmljivanja());
		Collections.sort(sortiranaIznajmljivanja, new SortByBrojSobeAndDatumZavrsetka());	
		return sortiranaIznajmljivanja;			
	}
	
	
	public class SortByBrojSobe implements Comparator<Iznajmljivanje>{

		@Override
		public int compare(Iznajmljivanje i1, Iznajmljivanje i2) {

			return i1.getSoba().getBroj().compareTo(i2.getSoba().getBroj());
		}
		
	}
	
	public class SortByTipSobe implements Comparator<Iznajmljivanje>{

		@Override
		public int compare(Iznajmljivanje i1, Iznajmljivanje i2) {

			return i1.getSoba().getTipSobe().getBrojKreveta() - i2.getSoba().getTipSobe().getBrojKreveta();
		}
		
	}
	
	public class SortByDatumPocetka implements Comparator<Iznajmljivanje>{

		@Override
		public int compare(Iznajmljivanje i1, Iznajmljivanje i2) {

			return i1.getDatumPocetka().compareTo(i2.getDatumPocetka());
		}
		
	}
	
	public class SortByDatumZavrsetka implements Comparator<Iznajmljivanje>{

		@Override
		public int compare(Iznajmljivanje i1, Iznajmljivanje i2) {

			return i1.getDatumZavrsetka().compareTo(i2.getDatumZavrsetka());
		}
		
	}
	
	public class SortByBrojSobeAndDatumPocetka implements Comparator<Iznajmljivanje>{

		@Override
		public int compare(Iznajmljivanje i1, Iznajmljivanje i2) {

			int comparator = i1.getSoba().getBroj().compareTo(i2.getSoba().getBroj());
			if(comparator == 0) {
				comparator= i1.getDatumPocetka().compareTo(i2.getDatumPocetka());
			}
			return comparator;
		}
		
	}
	
	public class SortByBrojSobeAndDatumZavrsetka implements Comparator<Iznajmljivanje>{
		
		@Override
		public int compare(Iznajmljivanje i1, Iznajmljivanje i2) {

			int comparator = i1.getSoba().getBroj().compareTo(i2.getSoba().getBroj());
			if(comparator == 0) {
				comparator= i1.getDatumZavrsetka().compareTo(i2.getDatumZavrsetka());
			}
			return comparator;
		}
		
		
	}

	public List<Iznajmljivanje> pretraga(String brojSobe, String datumPocetka, String datumZavrsetka, String brojLicneKarte) {
		LocalDateTime datumP=null;
		LocalDateTime datumZ=null;
		try {
			datumP = LocalDateTime.parse(datumPocetka, Printer.dateTimeFormatter);
		}catch (Exception e) {
			datumPocetka ="";
		}
		try {
			datumZ = LocalDateTime.parse(datumZavrsetka, Printer.dateTimeFormatter);
		}catch (Exception e) {
			
			datumZavrsetka="";
		}
		List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
		for(Iznajmljivanje i : getIznajmljivanja()) {
			boolean ok = i.getSoba().getBroj().contains(brojSobe);
			if(!datumPocetka.equals("") && !datumZavrsetka.equals("")) {
				ok = ok && i.getDatumZavrsetka().compareTo(datumZ) <= 0 && 
						   i.getDatumPocetka  ().compareTo(datumP) >= 0;
			}else {
				if(!datumPocetka.equals("")) {
					ok = ok && i.getDatumPocetka().compareTo(datumP) ==0;
				}
				if(!datumZavrsetka.equals("")) {
					ok = ok && i.getDatumZavrsetka().compareTo(datumZ) ==0;
				}
			}
			boolean pronasaoGosta = false;
			for(Osoba gost : i.getGosti()) {
				if(gost.getBrojLicneKarte().contains(brojLicneKarte)) {
					pronasaoGosta=true;
					break;
				}
			}
			ok = ok && pronasaoGosta;
			
			if(ok) {
				iznajmljivanja.add(i);
			}
		}
		return iznajmljivanja;
	}
	
}
