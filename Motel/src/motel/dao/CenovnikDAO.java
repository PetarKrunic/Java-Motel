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

import motel.model.StavkaCenovnika;
import motel.model.TipSobe;

public class CenovnikDAO {

	
	private TipSobeDAO tipSobeDAO;
	private final String datoteka = "cenovnik.txt";
	private List<StavkaCenovnika> cenovnik;
	
	
	public CenovnikDAO(TipSobeDAO tipSobeDAO) {
		this.tipSobeDAO = tipSobeDAO;
		cenovnik = ucitaj();
	}

	public List<StavkaCenovnika> getCenovnik() {
		return cenovnik.stream().filter(c -> !c.isObrisan()).collect(Collectors.toList());
	}
	
	public StavkaCenovnika getStavkaByDatum(LocalDateTime datumKreiranja) {
		for(StavkaCenovnika stavkaCenovnika : cenovnik) {
			if(stavkaCenovnika.getDatumKreiranja().isEqual(datumKreiranja)) {
				return stavkaCenovnika;
			}
		}
		return null;
	}
	
	public StavkaCenovnika getStavkaByTipSobe(TipSobe tipSobe) {
		StavkaCenovnika najnovijaStavka = null;
		int i;
		for(i=0; i< getCenovnik().size(); i++) {
			StavkaCenovnika stavkaCenovnika = getCenovnik().get(i);
			TipSobe t = stavkaCenovnika.getTipSobe();
			if(t.getNaziv().equals(tipSobe.getNaziv()) && t.getBrojKreveta() == tipSobe.getBrojKreveta()) {
				najnovijaStavka=stavkaCenovnika;
				break;
			}
		}
		for( ;i<getCenovnik().size(); i++) {
			StavkaCenovnika stavkaCenovnika = getCenovnik().get(i);
			TipSobe t = stavkaCenovnika.getTipSobe();
			if(t.getNaziv().equals(tipSobe.getNaziv()) && t.getBrojKreveta() == tipSobe.getBrojKreveta()) {
				if(najnovijaStavka.getDatumKreiranja().isBefore(stavkaCenovnika.getDatumKreiranja())) {
					najnovijaStavka=stavkaCenovnika;
				}
			}
		}
		return najnovijaStavka;
	}
	
	public void dodajCenovnik(StavkaCenovnika stavkaCenovnika) {
		this.cenovnik.add(stavkaCenovnika);
		sacuvaj();
	}
	
	public void obrisiCenovnik(int index) {
		cenovnik.get(index).setObrisan(true);
		sacuvaj();
		
	}

	public void izmeniCenovnik(StavkaCenovnika stavkaCenovnika , StavkaCenovnika novaStavkaCenovnika) {
		stavkaCenovnika.setDatumKreiranja(stavkaCenovnika.getDatumKreiranja());
		stavkaCenovnika.setDnevniBoravak(stavkaCenovnika.getDnevniBoravak());
		stavkaCenovnika.setNocenje(stavkaCenovnika.getNocenje());
		stavkaCenovnika.setVikendPoskupljenje(stavkaCenovnika.getVikendPoskupljenje());		
		stavkaCenovnika.setTipSobe(stavkaCenovnika.getTipSobe());
		sacuvaj();	
	}
	
	public void sacuvaj() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(datoteka));
			for(StavkaCenovnika stavkaCenovnika : cenovnik) {
				writer.println(stavkaCenovnika.ispis());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(writer !=null) {
				writer.close();
			}
			
		}
		
	}
	
	private List<StavkaCenovnika> ucitaj() {
		Scanner scanner = null;
		List<StavkaCenovnika> cenovnik= new ArrayList<>();
		try {
			File file = new File(datoteka);
			file.createNewFile();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
								
				boolean obrisan = Boolean.valueOf( tokenizer.nextToken().trim() );
				LocalDateTime datumKreiranja= LocalDateTime.parse(tokenizer.nextToken().trim());
				double dnevniBoravak = Double.parseDouble(tokenizer.nextToken().trim());
				double nocenje = Double.parseDouble(tokenizer.nextToken().trim());
				double vikendPoskupljenje = Double.parseDouble(tokenizer.nextToken().trim());
				tokenizer = new StringTokenizer(tokenizer.nextToken().trim(),":");
				String naziv = tokenizer.nextToken().trim();
				int brojKreveta = Integer.parseInt(tokenizer.nextToken().trim());
				TipSobe tipSobe = tipSobeDAO.getTipSobe(naziv, brojKreveta);
				StavkaCenovnika stavkaCenovnika = new StavkaCenovnika(datumKreiranja,dnevniBoravak, nocenje, vikendPoskupljenje, tipSobe,obrisan);
				cenovnik.add(stavkaCenovnika);						
			}
		}catch (Exception e) {
			
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		return cenovnik;
	}

	
}
