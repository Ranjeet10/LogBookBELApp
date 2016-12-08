package com.ranjeet.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	
	public static void main(String[] args) throws ParseException {
		
		String string = "11/02/2016";
		System.out.println(string);
		Date fromDateAsDate = new SimpleDateFormat("MM/dd/yyyy").parse(string);
		System.out.println(fromDateAsDate);
		
		String fromDateAsString = new SimpleDateFormat("yyyy-MM-dd").format(fromDateAsDate);

		System.out.println(fromDateAsString);
		
		
		
		
		
		//System.out.println(date);
		//SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		//Date date = format.parse(string);
		//System.out.println(date.getTime()); // Sat Jan 02 00:00:00 GMT 2010
		//System.out.println(java.sql.Date.valueOf(string));
	}

}
