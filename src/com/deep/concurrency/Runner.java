package com.deep.concurrency;

import java.util.concurrent.CountDownLatch;

// runner waits until the countdown timer gets to zero and then starts running 
public class Runner extends Thread{
	private CountDownLatch timer;
	private String name;

	public Runner(CountDownLatch cdl, String name)
	{
		timer = cdl;
		this.setName(name);
		System.out.println(this.getName()+"ready and waiting for countdown to start");
		start();
	}
	public void run()
	{
		try{
			timer.await();
		}
		catch(InterruptedException ie)
		{
			System.err.println("interrupted, cant start running the race");
		}
		System.out.println(this.getName()+" started racing");
	}
}
