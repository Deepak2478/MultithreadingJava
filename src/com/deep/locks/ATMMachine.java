package com.deep.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//simulates ATM machine where every person waits for its turn
public class ATMMachine {
	public static void main(String args[])
	{
		// a person can use machine again hence using reentrant lock
		Lock machine = new ReentrantLock();
		
		//list of people waiting to access the machine
		new Person(machine, "test1");
		new Person(machine, "test2");
		new Person(machine, "test3");
		new Person(machine, "test4");
	}
}
