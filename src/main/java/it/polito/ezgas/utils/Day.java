package it.polito.ezgas.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Day {
	
	public static int calculateDays(String s) throws ParseException {
		Date date =  new SimpleDateFormat("MM-dd-yyyy").parse(s);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date);
		long milliseconds = c1.getTimeInMillis() - c2.getTimeInMillis();
		int days = (int) (milliseconds / 86400000);
		return days;
	}
	
	public static String calendarToString() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		return dateFormat.format(date);
	}
}
