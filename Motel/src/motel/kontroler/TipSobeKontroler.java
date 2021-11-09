package motel.kontroler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motel.dao.TipSobeDAO;
import motel.model.TipSobe;

public class TipSobeKontroler {
	
	private TipSobeDAO tipSobeDAO;
	
	public TipSobeKontroler(TipSobeDAO tipSobeDAO) {
		this.tipSobeDAO = tipSobeDAO;
	}
	
	public TipSobe getTipSobe(int index) {
		if( index < 0 || index >= getTipoviSoba().size()) {
			return null;
		}
		return getTipoviSoba().get(index);
	}
	
	public List<TipSobe> getTipoviSoba(){
		return tipSobeDAO.getTipoviSoba();			
	}
	
	public boolean dodajTipSobe(TipSobe tipSobe) { 
		if( postojiTipSobe(tipSobe)) {
			return false;
		}
		tipSobeDAO.dodajTipSobe(tipSobe);
		return true;

	}

	private boolean postojiTipSobe(TipSobe tipSobe) {
		return getTipoviSoba()
				.stream()
				.anyMatch(t -> t.getNaziv().equals(tipSobe.getNaziv()) && t.getBrojKreveta() == tipSobe.getBrojKreveta());
	}

	public boolean obrisiTipSobe(int index) {
		if( index < 0 && index >= getTipoviSoba().size()) {
			return false;
		}
		tipSobeDAO.obrisiTipSobe(index);
		return true;
		
	}
	
	public boolean izmeniTipSobe(TipSobe tipSobe , TipSobe noviTipSobe) {
		if(tipSobe.getBrojKreveta() == noviTipSobe.getBrojKreveta() &&
		   tipSobe.getNaziv().equals(noviTipSobe.getNaziv())) {
			return true;
		}
		if( postojiTipSobe(noviTipSobe)) {
			return false;
		}
		tipSobeDAO.izmeniTipSobe(tipSobe,noviTipSobe);
		return true;
		
	}
	
	public List<TipSobe> getSortiraneTipoviSobaPoNazivu(){
		List<TipSobe> sortiraneTipoviSoba = new ArrayList<>(this.tipSobeDAO.getTipoviSoba());
		Collections.sort(sortiraneTipoviSoba, new SortByNaziv());	
		return sortiraneTipoviSoba;			
	}
	
	public List<TipSobe>  getSortiraneTipoviSobaPoBroju(){
		List<TipSobe> sortiraneTipoviSoba = new ArrayList<>(this.tipSobeDAO.getTipoviSoba());
		Collections.sort(sortiraneTipoviSoba, new SortByBroj());	
		return sortiraneTipoviSoba;
	}
	
	public List<TipSobe> getSortiraneTipoviSobaPoNazivuIBroju(){
		List<TipSobe> sortiraneTipoviSoba = new ArrayList<>(this.tipSobeDAO.getTipoviSoba());
		Collections.sort(sortiraneTipoviSoba, new SortByNazivAndBroj());	
		return sortiraneTipoviSoba;			
	}
	
	
	public class SortByBroj implements Comparator<TipSobe>{

		@Override
		public int compare(TipSobe t1, TipSobe t2) {

			return t1.getBrojKreveta() - t2.getBrojKreveta(); //sortira po rastucem tj. ako t1-t2 je poz broj t1>t2 pa ide t2,t1
		}
		
	}
	
	public class SortByNaziv implements Comparator<TipSobe>{

		@Override
		public int compare(TipSobe t1, TipSobe t2) {

			return t1.getNaziv().compareTo(t2.getNaziv());
		}
		
	}
	
	public class SortByNazivAndBroj implements Comparator<TipSobe>{

		@Override
		public int compare(TipSobe t1, TipSobe t2) {

			int comparator = t1.getNaziv().compareTo(t2.getNaziv());
			if(comparator == 0) {
				comparator = t1.getBrojKreveta() - t2.getBrojKreveta();
			}
			return comparator;
		}
		
	}


}
