package com.deep.locks;

import java.util.concurrent.locks.Lock;

// each person is an independent thread and its access
//to ATM needs to be synchronized using a lock
public class Person extends Thread{

	private Lock machine;
	public Person(Lock machine, String name)
	{
		this.machine = machine;
		this.setName(name);
		this.start();
	}
	public void run()
	{
		try
		{
			System.out.println(getName()+" waiting to access the ATM machine");
			machine.lock();
			System.out.println(getName()+" is accessing the machine");
			Thread.sleep(1000);//simulate time required for withdrawl
		}
		catch(InterruptedException ir)
		{
			System.err.println(ir);
		}
		finally{
			System.out.println(getName()+" i done using ATM machine");
			machine.unlock();
		}
		
	}
}
