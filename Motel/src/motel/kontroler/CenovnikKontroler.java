package motel.kontroler;

import java.util.List;

import motel.dao.CenovnikDAO;
import motel.model.StavkaCenovnika;

public class CenovnikKontroler {

	private CenovnikDAO cenovnikDAO;
	public CenovnikKontroler(CenovnikDAO cenovnikaDAO) {
		this.cenovnikDAO = cenovnikaDAO;
	}

	
	public StavkaCenovnika getStavka(int index) {

		if( index < 0 || index > cenovnikDAO.getCenovnik().size()-1) {
			return null;
		}
		
		return cenovnikDAO.getCenovnik().get(index);
	}
	
	public List<StavkaCenovnika> getCenovnik(){
		return cenovnikDAO.getCenovnik();			
	}
	
	public void dodajStavku(StavkaCenovnika stavkaCenovnika) { 
		cenovnikDAO.dodajCenovnik(stavkaCenovnika);

	}

	public boolean obrisiStavku(int index) {
		if( index < 0 || index > cenovnikDAO.getCenovnik().size()-1) {
			return false;
		}
		
		cenovnikDAO.obrisiCenovnik(index);
		return true;
		
	}
	
	public void izmeniStavku(StavkaCenovnika stavkaCenovnika , StavkaCenovnika novaStavkaCenovnika) {

		cenovnikDAO.izmeniCenovnik(stavkaCenovnika, novaStavkaCenovnika);
		
	}

}
