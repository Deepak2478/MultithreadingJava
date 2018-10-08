package com.deep.ivw.threadLocale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 *  thread safe implementation of simple date formatter
 *  Each thread will get its own instance of SimpleDateFormatter
 *  which wil not be shared between threads
 */
public class PerThreadFormatter {
	private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = 
			new ThreadLocal<SimpleDateFormat>(){
			
			@Override
			protected SimpleDateFormat initialValue(){
				System.out.println("Creating initial value : "+Thread.currentThread().getName());
				return new SimpleDateFormat("dd/MM/yyyy");
			}
	};
	/*
	 * Everytime there is call for DateFormat, ThreadLocal will return calling
	 *  thread's copy of SimpleDateFormat
	 */
	
	public static DateFormat getDateFormatter(){
		return dateFormatHolder.get();
	}

}
