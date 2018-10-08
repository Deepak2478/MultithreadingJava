package com.deep.concurrency;

// The run method should be called when 4 players are ready to start a game
public class MixedDoubleTennisGame extends Thread {
	public void run()
	{
		System.out.println("All 4 players are ready, game starts");
	}
}
