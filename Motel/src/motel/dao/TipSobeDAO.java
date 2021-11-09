package motel.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import motel.model.TipSobe;

public class TipSobeDAO {
	private final String datoteka = "tipSobe.txt";
	private List<TipSobe> tipoviSoba;
	
	public TipSobeDAO() {
		tipoviSoba = ucitaj();	
	}
	
	public List<TipSobe> getTipoviSoba() {
		return tipoviSoba.stream().filter(t -> !t.isObrisan()).collect(Collectors.toList());
	}
	
	public TipSobe getTipSobe(String naziv, int brojKreveta) {
		for(TipSobe tipSobe : getTipoviSoba()) {
			if(tipSobe.getNaziv().equals(naziv) && tipSobe.getBrojKreveta() == brojKreveta) {
				return tipSobe;
			}
		}
		return null;
	}
	
	public void dodajTipSobe(TipSobe tipSobe) {
		this.tipoviSoba.add(tipSobe);
		sacuvaj();
	}
	
	public void obrisiTipSobe(int index) {
		getTipoviSoba().get(index).setObrisan(true);
		sacuvaj();	
	}
	
	public void izmeniTipSobe(TipSobe tipSobe, TipSobe noviTipSobe) {
		tipSobe.setNaziv(noviTipSobe.getNaziv());
		tipSobe.setBrojKreveta(tipSobe.getBrojKreveta());
		sacuvaj();
	}
	
	public void sacuvaj() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(datoteka));
			for(TipSobe tipSobe : tipoviSoba) {
				writer.println(tipSobe.ispis());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(writer !=null) {
				writer.close();
			}
			
		}
		
	}
	
	private List<TipSobe> ucitaj() {
		Scanner scanner = null;
		List<TipSobe> tipoviSoba = new ArrayList<>();
		try {
			File file = new File(datoteka);
			file.createNewFile();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
								
				boolean obrisan = Boolean.valueOf( tokenizer.nextToken().trim() );
				String naziv = tokenizer.nextToken().trim();
				int brojKreveta = Integer.parseInt(tokenizer.nextToken().trim());
				
				TipSobe tipSobe = new TipSobe(naziv, brojKreveta, obrisan);
				tipoviSoba.add(tipSobe);						
			}
		}catch (Exception e) {
			
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		return tipoviSoba;
	}

	

	
}
