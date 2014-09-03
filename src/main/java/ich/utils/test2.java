package ich.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class test2 {

	public static void main(String[] args) {


		long timeinMillis=System.currentTimeMillis();
		System.out.println(timeinMillis);
		Timestamp ts=new Timestamp(timeinMillis);
		System.out.println(ts);
		System.out.println(ts.getDate()+"-"+(ts.getMonth()+1)+"-"+(ts.getYear()+1900));
		System.out.println(ts.getHours()+":"+ts.getMinutes());
		
		
		
		System.out.println("\n\n\n");
		
		DateFormat df = new SimpleDateFormat("HH:mm");
		String formatted = df.format(new Date(timeinMillis));
		System.out.println(formatted);

		
		df = new SimpleDateFormat("dd-MMM-yy");
		formatted = df.format(new Date(timeinMillis));
		System.out.println(formatted);
		
		/*System.out.println("\n\n\n");
		SimpleDateFormat readFormatter = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat readFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = "7-07-2013";
	 
		try {
	 
			Date date = readFormatter.parse(dateInString);
			System.out.println(date);
			
			
			
			System.out.println(readFormatter.format(date));
	 
		} catch (ParseException e) {
			e.printStackTrace();
		} */
		
	}

}
