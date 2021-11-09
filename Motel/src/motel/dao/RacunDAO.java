package motel.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import motel.model.Iznajmljivanje;
import motel.model.Osoba;
import motel.model.Racun;


public class RacunDAO {

	private List<Racun> racuni;
	private final String datoteka = "racuni.txt";
	private IznajmljivanjeDAO iznajmljivanjeDAO;
	
	public RacunDAO(IznajmljivanjeDAO iznajmljivanjeDAO) {
		this.iznajmljivanjeDAO = iznajmljivanjeDAO;
		this.racuni = ucitaj();
	}
	public Racun getRacun(LocalDateTime datumIzdavanja) {
		for(Racun racun : racuni ) {
			if(racun.getDatumIzdavanja().isEqual(datumIzdavanja)) {
				return racun;
			}
		}
		return null;
	}

	public List<Racun> getRacuni() {
		return racuni;		
	}

	public void dodajRacun(Racun racun) {
		racuni.add(racun);	
		sacuvaj();
	}
	
	public void sacuvaj() {
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(datoteka));
			for(Racun racun: racuni) {
				writer.println(racun.ispis());
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(writer !=null) {
				writer.close();
			}
			
		}
		
	}
	
	private List<Racun> ucitaj() {
		Scanner scanner = null;
		List<Racun> racuni= new ArrayList<>();
		try {
			File file = new File(datoteka);
			file.createNewFile();
			scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "|");
								
				LocalDateTime datumIzdavanja= LocalDateTime.parse(tokenizer.nextToken().trim());
				double ukupnaCena = Double.parseDouble(tokenizer.nextToken().trim());
				
				String iznajmljivanjeString = tokenizer.nextToken().trim();
				int index = iznajmljivanjeString.lastIndexOf(':');
				LocalDateTime datumPocetka= LocalDateTime.parse(iznajmljivanjeString.substring(0, index));
				String brojSobe = iznajmljivanjeString.substring(index+1, iznajmljivanjeString.length());
				Iznajmljivanje iznajmljivanje = iznajmljivanjeDAO.getIznajmljivanjeByDatumAndSobaBroj(datumPocetka, brojSobe);
				
				StringTokenizer  innerTokenizer = new StringTokenizer(tokenizer.nextToken().trim(),":");
				String ime = innerTokenizer.nextToken().trim();
				String prezime = innerTokenizer.nextToken().trim();
				String brojLicneKarte = innerTokenizer.nextToken().trim();
				Osoba osoba = new Osoba(ime, prezime, brojLicneKarte);

				Racun racun = new Racun(datumIzdavanja, ukupnaCena, iznajmljivanje, osoba);
				racuni.add(racun);
			}
		}catch (Exception e) {
			
		}finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		return racuni;
	}

	

}
