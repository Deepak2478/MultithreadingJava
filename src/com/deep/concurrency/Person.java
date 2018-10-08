package com.deep.concurrency;

import java.util.concurrent.Semaphore;

public class Person extends Thread{
	private Semaphore machines;
	public Person(Semaphore machines, String name)
	{
		this.machines = machines;
		this.setName(name);
		this.start();
	}
	public void run(){
		try
		{
			System.out.println(getName()+" waiting to access an ATM machine");
			machines.acquire();
			System.out.println(getName()+" is accessing the ATM machine");
			Thread.sleep(1000);
			System.out.println(getName()+" is done using the ATM machine");
			machines.release();
		}
		catch(InterruptedException ir)
		{
			System.err.println(ir);
		}
		
	}
}
