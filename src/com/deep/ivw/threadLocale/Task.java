package com.deep.ivw.threadLocale;

import java.util.Date;

public class Task implements Runnable {

	@Override
	public void run() {
		int i=0;
		while(i<2){
			System.out.println("Thread :"+Thread.currentThread().getName() +
					" Date : "+ThreadLocalTest.threadSafeFormat(new Date()));
		}

	}

}
