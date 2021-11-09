package motel.kontroler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import motel.dao.SobaDAO;
import motel.model.Soba;

public class SobaKontroler {
	
	private SobaDAO sobaDAO;
	
	public SobaKontroler(SobaDAO sobaDAO) {
		this.sobaDAO = sobaDAO;
	}
	
	public Soba getSoba(String broj) {
		for(Soba soba : sobaDAO.getSobe()) {
			if(soba.getBroj().equals(broj)) {
				return soba;
			}
		}
		
		return null;
	}
	
	public List<Soba> getSobe(){
		return sobaDAO.getSobe();			
	}
	
	public boolean dodajSobu(Soba soba) { 
		if( postojiSoba(soba.getBroj())) {
			return false;
		}
		sobaDAO.dodajSobu(soba);
		return true;

	}

	public boolean postojiSoba(String broj) {
		return sobaDAO.getSobe()
				.stream()
				.anyMatch(s -> s.getBroj().equals(broj));
	}

	public boolean obrisiSobu(String broj) {
		if(!postojiSoba(broj)) {
			return false;
		}
		sobaDAO.obrisiSobu(broj);
		return true;
		
	}
	
	public boolean izmeniSobu(Soba soba , Soba novaSoba) {
		if( !soba.getBroj().equals(novaSoba.getBroj()) && postojiSoba(novaSoba.getBroj())) {
			return false;
		}
		sobaDAO.izmeniSobu(soba, novaSoba);
		return true;
		
	}
	
	public List<Soba> getSortiraneSobePoTipu(){
		List<Soba> sortiraneSobe = new ArrayList<>(this.sobaDAO.getSobe());
		Collections.sort(sortiraneSobe, new SortByTip());	
		return sortiraneSobe;			
	}
	
	public List<Soba> getSortiraneSobePoBroju(){
		List<Soba> sortiraneSobe = new ArrayList<>(this.sobaDAO.getSobe());
		Collections.sort(sortiraneSobe, new SortByBroj());	
		return sortiraneSobe;
	}
	
	public class SortByBroj implements Comparator<Soba>{

		@Override
		public int compare(Soba s1, Soba s2) {

			return s1.getBroj().compareTo(s2.getBroj());
		}
		
	}
	
	public class SortByTip implements Comparator<Soba>{

		@Override
		public int compare(Soba s1, Soba s2) {

			return s1.getTipSobe().getBrojKreveta() - s2.getTipSobe().getBrojKreveta();
		}
		
	}

	public List<Soba> pretraga(String brojSobe, String brojKreveta, String tv, String miniBar) {
		Integer ibrojKreveta=0;
		try {
			ibrojKreveta = Integer.parseInt(brojKreveta);
		}catch (Exception e) {
			brojKreveta ="";
		}
		boolean tvBoolean = tv.equalsIgnoreCase("da");
		boolean miniBarBoolean = miniBar.equalsIgnoreCase("da");
		
		List<Soba> sobe = new ArrayList<>();
		for(Soba s : getSobe()) {
			boolean ok = s.getBroj().contains(brojSobe)	;
			if(!brojKreveta.equals("")) {
				ok = ok && ibrojKreveta == s.getTipSobe().getBrojKreveta();
			}
			if(!tv.equals("")) {
				ok = ok && tvBoolean == s.isTv();
			}
			if(!miniBar.equals("")) {
				ok = ok && miniBarBoolean== s.isMiniBar();
			}

			if(ok) {
				sobe.add(s);
			}
		}
		return sobe;
		
	}
	
	

}
