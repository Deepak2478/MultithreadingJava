package com.deep.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// stimulates arrival of trains on railway station
public class RailwayStation {
	// a common lock for synchronization
	private static Lock station = new ReentrantLock();
	
	// condition to wait or notify the arrival of Joe in the station
	private static Condition joeArrival = station.newCondition();
	
	//Train class simulates arrival of trains independently
	static class Train extends Thread{
		public Train(String name)
		{
			this.setName(name);
		}
		public void run()
		{
			station.lock();
			try
			{
				System.out.println(getName()+" : I have arrived at th station");
				if(getName().startsWith("IC1122"))
				{
					// Joe is coming in train IC1122 = he announces it to us
					joeArrival.signalAll();
				}
			}
			finally{
				station.unlock();
			}
		}
	}
	
	// our wait in railway station for Joe is simulated by this thread.
	// once we get notification from Joe that he has arrived, we pick him up and go home
	static class WaitForJoe extends Thread
	{
		public void run()
		{
			System.out.println("Waiting in the station where Joe is coming");
			station.lock();
			try{
				//awaiting Joe's train arrival
				joeArrival.await();
				System.out.println("Pick up joe and go home");
			}
			catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
			finally{
				station.unlock();
			}
		}
	}
	public static void main(String args[])
	{
		//we are waiting before train transit starts coming
		new WaitForJoe().start();
		//trains are separate threads and can arrive in any order
		new Train("Train1").start();
		new Train("Train2").start();
		new Train("Train3").start();
		new Train("Train4").start();
	}
}
