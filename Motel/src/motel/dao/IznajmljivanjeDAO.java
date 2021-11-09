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

import motel.model.Iznajmljivanje;
import motel.model.Osoba;
import motel.model.Soba;

public class IznajmljivanjeDAO {

	private SobaDAO sobaDAO;
	private final String datoteka = "iznajmljivanja.txt";
	private List<Iznajmljivanje> iznajmljivnja;
	public IznajmljivanjeDAO(SobaDAO sobaDAO) {
		this.sobaDAO = sobaDAO;
		iznajmljivnja = ucitaj();
	}

	public List<Iznajmljivanje> getIznajmljivanja() {
		return iznajmljivnja.stream().filter(i -> !i.isObrisan()).collect(Collectors.toList());
	}
	
	
	public Iznajmljivanje getIznajmljivanjeByDatumAndSobaBroj(LocalDateTime datumPocetka, String brojSobe) {
		for(Iznajmljivanje iznajmljivanje : getIznajmljivanja()) {
			if(iznajmljivanje.getDatumPocetka().isEqual(datumPocetka) && 
			   iznajmljivanje.getSoba().getBroj().equals(brojSobe)) {
				return iznajmljivanje;
			}
		}
		return null;
	}
	
	public void dodajIznajmljivanje(Iznajmljivanje iznajmljivanje) {
		this.iznajmljivnja.add(iznajmljivanje);
		sacuvaj();
	}
	
	public void obrisiIznamljivanje(int index) {
		iznajmljivnja.get(index).setObrisan(true);
		sacuvaj();
		
	}

	public void izmeniIznajmljivanje(Iznajmljivanje iznajmljivanje , Iznajmljivanje novoIznajmljivanje) {
		iznajmljivanje.setDatumPocetka(novoIznajmljivanje.getDatumPocetka());
		iznajmljivanje.setDatumZavrsetka(novoIznajmljivanje.getDatumZavrsetka());
		iznajmljivanje.setSoba(novoIznajmljivanje.getSoba());
		iznajmljivanje.setGosti(novoIznajmljivanje.getGosti());
		sacuvaj();	
	}
	
	public void sacuvaj() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(datoteka));
			for(Iznajmljivanje iznajmljivanje: iznajmljivnja) {
				writer.println(iznajmljivanje.ispis());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(writer !=null) {
				writer.close();
			}
			
		}
		
	}
	
	private List<Iznajmljivanje> ucitaj() {
		Scanner scanner = null;
		List<Iznajmljivanje> iznajmljivanja= new ArrayList<>();
		try {
			File file = new File(datoteka);
			file.createNewFile();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
								
				boolean obrisan = Boolean.valueOf( tokenizer.nextToken().trim() );
				LocalDateTime datumPocetka= LocalDateTime.parse(tokenizer.nextToken().trim());
				LocalDateTime datumZavrsetka= LocalDateTime.parse(tokenizer.nextToken().trim());
				String brojSobe = tokenizer.nextToken().trim();
				Soba soba = sobaDAO.getSoba(brojSobe);
				List<Osoba> gosti =  new ArrayList<>();
				tokenizer = new StringTokenizer(tokenizer.nextToken().trim(),";");
				while(tokenizer.hasMoreTokens()) {
					StringTokenizer innerTokenizer = new StringTokenizer(tokenizer.nextToken(), ":");
					String ime = innerTokenizer.nextToken().trim();
					String prezime = innerTokenizer.nextToken().trim();
					String brojLicneKarte = innerTokenizer.nextToken().trim();
					Osoba gost = new Osoba(ime, prezime, brojLicneKarte);
					gosti.add(gost);
				}
				
				
				Iznajmljivanje iznajmljivanje = new Iznajmljivanje(gosti, soba, datumPocetka, datumZavrsetka, obrisan);
				iznajmljivanja.add(iznajmljivanje);						
			}
		}catch (Exception e) {
			
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		return iznajmljivanja;
	}

	


}
