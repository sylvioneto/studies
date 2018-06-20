package java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Datas {

	public static void main(String[] args) {
		
		// JAVA 8 dates
	
		
		// get current date
		LocalDate today = LocalDate.now();
		System.out.println("Current date: "+today);
		
		// get current datetime
		LocalDateTime todayNow = LocalDateTime.now();
		System.out.println("Current date time: "+todayNow);
		
		// create a date
		LocalDate ftDate = LocalDate.of(2099, Month.JANUARY, 25);
		System.out.println("ftDate: "+ftDate);
		
		// period between dates
		Period period = Period.between(today, ftDate);
		System.out.println("Period: "+period);
		
		// formatting dates
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatDatetime = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		System.out.println(formatDate.format(today));
		System.out.println(formatDatetime.format(todayNow));
		
	}

}
