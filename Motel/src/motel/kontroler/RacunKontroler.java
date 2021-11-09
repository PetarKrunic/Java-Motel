package motel.kontroler;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motel.dao.CenovnikDAO;
import motel.dao.RacunDAO;
import motel.model.Iznajmljivanje;
import motel.model.Racun;
import motel.model.StavkaCenovnika;
import motel.model.TipSobe;
import motel.utils.Printer;

public class RacunKontroler {

	private RacunDAO racunDAO;
	private CenovnikDAO cenovnikDAO;
	public RacunKontroler(RacunDAO racunDAO,CenovnikDAO cenovnikDAO) {
		this.racunDAO = racunDAO;
		this.cenovnikDAO = cenovnikDAO;
	}

	public List<Racun> getRacuni() {
		return racunDAO.getRacuni();
	}
	
	public void dodajRacun(Racun racun) { 
		racunDAO.dodajRacun(racun);
	}

	public double izracunajUkupnuCenu(Iznajmljivanje iznajmljivanje) {
		LocalDateTime datumPocetka = iznajmljivanje.getDatumPocetka();
		LocalDateTime datumZavrsetka = iznajmljivanje.getDatumZavrsetka();
		TipSobe tipSobe = iznajmljivanje.getSoba().getTipSobe();
		StavkaCenovnika stavkaCenovnika = cenovnikDAO.getStavkaByTipSobe(tipSobe);
		double cenaDnevnogBoravka = stavkaCenovnika.getDnevniBoravak();
		double cenaNocenja = stavkaCenovnika.getNocenje();
		double udeoVikenda = stavkaCenovnika.getVikendPoskupljenje()/100;
		int brojRadnihDana=0;
		int brojNeRadnihDana = 0;
		LocalDateTime datum = LocalDateTime.parse(datumPocetka.toString());
		if(ChronoUnit.HOURS.between(datumPocetka, datumZavrsetka) <= 10  && datumZavrsetka.getHour() < 22) {
			if(datum.getDayOfWeek().equals(DayOfWeek.SATURDAY) || datum.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				return cenaDnevnogBoravka + cenaDnevnogBoravka*udeoVikenda;
			 }else {
				 return cenaDnevnogBoravka;
			 }

		}
		while( datum.toLocalDate().compareTo(datumZavrsetka.toLocalDate()) <= 0 ) {
			 if(datum.getDayOfWeek().equals(DayOfWeek.SATURDAY) || datum.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				 brojNeRadnihDana +=1;
			 }else {
				 brojRadnihDana +=1;
			 }
			 datum = datum.plusDays(1);
		 }
		 if(datumPocetka.toLocalTime().compareTo(datumZavrsetka.toLocalTime()) > 0 ) {
			 if(datumZavrsetka.getDayOfWeek().equals(DayOfWeek.SATURDAY) || datumZavrsetka.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				 brojNeRadnihDana--;
			 }else {
				 brojRadnihDana --;
			 }
		 }
		 
		 return (brojRadnihDana) * cenaNocenja + (brojNeRadnihDana)*(cenaNocenja+cenaNocenja*udeoVikenda);
		
	}
	
	public List<Racun> getSortiraniRacuniPoDatumu(){
		List<Racun> sortiranRacuni = new ArrayList<>(this.racunDAO.getRacuni());
		Collections.sort(sortiranRacuni, new SortByDatum());	
		return sortiranRacuni;			
	}
	
	public List<Racun> getSortiraniRacuniPoBrojuSobe(){
		List<Racun> sortiranRacuni = new ArrayList<>(this.racunDAO.getRacuni());
		Collections.sort(sortiranRacuni, new SortByBrojSobe());	
		return sortiranRacuni;			
	}
	
	public List<Racun> getSortiraniRacuniPoTipuSobe(){
		List<Racun> sortiranRacuni = new ArrayList<>(this.racunDAO.getRacuni());
		Collections.sort(sortiranRacuni, new SortByTipSobe());	
		return sortiranRacuni;			
	}
	
	public List<Racun> getSortiraniRacuniPoCeni(){
		List<Racun> sortiranRacuni = new ArrayList<>(this.racunDAO.getRacuni());
		Collections.sort(sortiranRacuni, new SortByCena());	
		return sortiranRacuni;			
	}
	
	public List<Racun> getSortiraniRacuniPoImeIPrezime(){
		List<Racun> sortiranRacuni = new ArrayList<>(this.racunDAO.getRacuni());
		Collections.sort(sortiranRacuni, new SortByImeAndPrezime());	
		return sortiranRacuni;			
	}
	
	
	public class SortByDatum implements Comparator<Racun>{

		@Override
		public int compare(Racun r1, Racun r2) {

			return r1.getDatumIzdavanja().compareTo(r2.getDatumIzdavanja());
		}
		
	}
	
	public class SortByCena implements Comparator<Racun>{

		@Override
		public int compare(Racun r1, Racun r2) {

			return (int) Math.round( r1.getUkupnaCena() - r2.getUkupnaCena());
		}
		
	}
	
	public class SortByBrojSobe implements Comparator<Racun>{

		@Override
		public int compare(Racun r1, Racun r2) {

			return r1.getIznajmljivanje().getSoba().getBroj().compareTo(r2.getIznajmljivanje().getSoba().getBroj());
		}
		
	}
	
	public class SortByTipSobe implements Comparator<Racun>{

		@Override
		public int compare(Racun r1, Racun r2) {

			return r1.getIznajmljivanje().getSoba().getTipSobe().getBrojKreveta() - r2.getIznajmljivanje().getSoba().getTipSobe().getBrojKreveta();
		}
		
	}
	
	public class SortByImeAndPrezime implements Comparator<Racun>{

		@Override
		public int compare(Racun r1, Racun r2) {

			int comparator =r1.getOsoba().getIme().compareTo(r2.getOsoba().getIme());
			if(comparator == 0) {
				comparator = r1.getOsoba().getPrezime().compareTo(r2.getOsoba().getPrezime());
			}
			return comparator;
		}
		
	}

	public List<Racun> pretraga(String datumOd, String datumDo, String ukupnaCenaOd, String ukupnaCenaDo,
			String brojSobe, String brojKreveta, String ime, String prezime) {
		
		LocalDateTime datumP=null;
		LocalDateTime datumZ=null;
		
		int brojK=0;
		double cenaOd=0;
		double cenaDo=0;
		
		try {datumP = LocalDateTime.parse(datumOd, Printer.dateTimeFormatter);}catch (Exception e) {datumOd ="";}
		try {datumZ = LocalDateTime.parse(datumDo, Printer.dateTimeFormatter);}catch (Exception e) {datumDo="";}
		
		try {brojK = Integer.parseInt(brojKreveta);}  catch(Exception e) {brojKreveta = "";}
		try {cenaOd = Double.parseDouble(ukupnaCenaOd);}catch(Exception e) {ukupnaCenaOd = "";}
		try {cenaDo = Double.parseDouble(ukupnaCenaDo);}catch(Exception e) {ukupnaCenaDo= "";}
		
		List<Racun> racuni = new ArrayList<>();
		for(Racun r : getRacuni()) {
			boolean ok = r.getIznajmljivanje().getSoba().getBroj().contains(brojSobe);
			if(!brojKreveta.equals("")) {
				ok = ok && r.getIznajmljivanje().getSoba().getTipSobe().getBrojKreveta() == brojK;
			}
			
			if(!datumOd.equals("") && !datumDo.equals("")) {
				ok = ok && r.getDatumIzdavanja().compareTo(datumZ) <= 0 && 
						   r.getDatumIzdavanja().compareTo(datumP) >= 0;
			}else {
				if(!datumOd.equals("")) {
					ok = ok && r.getDatumIzdavanja().compareTo(datumP) ==0;
				}
				if(!datumDo.equals("")) {
					ok = ok && r.getDatumIzdavanja().compareTo(datumZ) ==0;
				}
			}
			
			if(!ukupnaCenaOd.equals("") && !ukupnaCenaDo.equals("")) {
				ok = ok && r.getUkupnaCena() <= cenaDo && 
						r.getUkupnaCena() >= cenaOd ;
			}else {
				if(!ukupnaCenaOd.equals("")) {
					ok = ok && r.getUkupnaCena() == cenaOd;
				}
				if(!ukupnaCenaDo.equals("")) {
					ok = ok && r.getUkupnaCena() == cenaDo;
				}
			}
			
			
			ok = ok && r.getOsoba().getIme().contains(ime);
			ok = ok && r.getOsoba().getPrezime().contains(prezime);
			
			if(ok) {
				racuni.add(r);
			}
		}
		return racuni;
	}

}
