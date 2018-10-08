package com.deep.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//This thread simulates player's arrival
//once a player arrives, he should wait for all other players to arrive
public class Player extends Thread{

	CyclicBarrier waitPoint;
	public Player(CyclicBarrier barrier, String name)
	{
		this.setName(name);
		waitPoint = barrier;
		this.start();
	}
	public void run()
	{
		System.out.println("Player "+getName()+" is ready");
		try{
		 waitPoint.await();	// wait for all 4 players to join
		}
		catch(BrokenBarrierException |InterruptedException exception)
		{
			System.out.println("An exception occoured during waiting.."+exception);
		}
	}
}
