package motel.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import motel.model.Soba;
import motel.model.TipSobe;

public class SobaDAO {
	
	private TipSobeDAO tipSobeDAO;
	private final String datoteka = "sobe.txt";
	private List<Soba> sobe;
	
	public SobaDAO(TipSobeDAO tipSobeDAO) {
		this.tipSobeDAO = tipSobeDAO;
		sobe = ucitaj();
		
	}
	
	public List<Soba> getSobe() {
		return sobe.stream().filter(s -> !s.isObrisan()).collect(Collectors.toList());
	}
	
	public Soba getSoba(String brojSobe) {
		for(Soba soba : sobe) {
			if(soba.getBroj().equals(brojSobe)) {
				return soba;
			}
		}
		return null;
	}
	
	
	public void dodajSobu(Soba soba) {
		this.sobe.add(soba);
		sacuvaj();
	}
	
	public void obrisiSobu(String broj) {
		for(Soba soba : getSobe()) {
			if(soba.getBroj().equals(broj)) {
				soba.setObrisan(true);
				sacuvaj();
				break;
			}
		}
		
	}

	public void izmeniSobu(Soba soba, Soba novaSoba) {
		soba.setBroj(novaSoba.getBroj());
		soba.setTv(novaSoba.isTv());
		soba.setMiniBar(novaSoba.isMiniBar());
		soba.setTipSobe(novaSoba.getTipSobe());
		sacuvaj();	
	}
	
	public void sacuvaj() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(datoteka));
			for(Soba soba : sobe) {
				writer.println(soba.ispis());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(writer !=null) {
				writer.close();
			}
			
		}
		
	}
	
	private List<Soba> ucitaj() {
		Scanner scanner = null;
		List<Soba> sobe = new ArrayList<>();
		try {
			File file = new File(datoteka);
			file.createNewFile();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
								
				boolean obrisan = Boolean.valueOf( tokenizer.nextToken().trim() );
				String broj = tokenizer.nextToken().trim();
				boolean tv = Boolean.valueOf( tokenizer.nextToken().trim() );
				boolean miniBar = Boolean.valueOf( tokenizer.nextToken().trim() );
				tokenizer = new StringTokenizer(tokenizer.nextToken(), ":");
				String naziv = tokenizer.nextToken().trim();
				int brojKreveta = Integer.parseInt(tokenizer.nextToken().trim());
				TipSobe tipSobe = tipSobeDAO.getTipSobe(naziv, brojKreveta);
				Soba soba = new Soba(broj, tv, miniBar, tipSobe, obrisan);
				sobe.add(soba);						
			}
		}catch (Exception e) {
			
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		return sobe;
	}

	
}
