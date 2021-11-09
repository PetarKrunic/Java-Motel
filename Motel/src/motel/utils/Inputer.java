package motel.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Inputer {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static String getString() {
		while(true) {
			String string = scanner.nextLine();
			if(!string.isEmpty()) {
				return string;
			}
			System.out.print(" Morate uneti vrednost! ->");
		}
	}
	
	public static String getEmptyOrString() {
		return scanner.nextLine();
	
	}
	
	public static int getInt() {
		while(true) {
			String string = scanner.nextLine();
			try {
				int broj = Integer.parseInt(string);
				return broj;
			}catch (NumberFormatException e) {
				System.out.print(" Morate uneti ceo broj! ->");
				continue;
			}
		}
	}
	
	public static double getDouble() {
		while(true) {
			String string = scanner.nextLine();
			try {
				double broj = Double.parseDouble(string);
				return broj;
			}catch (NumberFormatException e) {
				System.out.print(" Morate uneti realan broj! ->");
				continue;
			}
		}
	}
	
	public static LocalDateTime getDateTime() {
		while(true) {
			String string = scanner.nextLine();
			try {
				LocalDateTime dateTime = LocalDateTime.parse(string, Printer.dateTimeFormatter);
				return dateTime;
			}catch (DateTimeParseException  e) {
				System.out.print(" Morate uneti datum u formatu "+Printer.dateTimeFormatter.toString()+" ! ->");
				continue;
			}
			
		}
	}

}
