package com.deep.concurrency;

import java.util.concurrent.Semaphore;

public class ATMRoom {
	public static void main(String args[])
	{
		//assume that only 2 ATM machines are availaible
		Semaphore machines = new Semaphore(2);
		//list of people accessing th machine
		new Person(machines,"test1");
		new Person(machines,"test2");
		new Person(machines,"test3");
		new Person(machines,"test4");
		new Person(machines,"test5");
	}
}
