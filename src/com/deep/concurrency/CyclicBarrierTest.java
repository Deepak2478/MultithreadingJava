package com.deep.concurrency;

import java.util.concurrent.CyclicBarrier;

//creates a cyclic barrier by passing the number of threads and the
//threads to run when all threads reach the barrier
public class CyclicBarrierTest {
	public static void main(String args[])
	{
		System.out.println("Retrieving tennis court as soon as all 4 players arrive");
		CyclicBarrier barrier = new CyclicBarrier(4, new MixedDoubleTennisGame());
		new Player(barrier, "test1");
		new Player(barrier, "test2");
		new Player(barrier, "test3");
		new Player(barrier, "test4");
	}
}
