package com.deep.concurrency;

import java.util.concurrent.CountDownLatch;

public class RuningRaceStarter {

	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch counter  = new CountDownLatch(5);
		new Runner(counter,"test1");
		new Runner(counter,"test2");
		new Runner(counter,"test3");
		
		System.out.println("Starting the countdown");
		long countVal = counter.getCount();
		while(countVal > 0)
		{
			Thread.sleep(1000);
			System.out.println(countVal);
			if(countVal == 1)
			{
				//once counter.countDown() in the next statement is called,
				// count down will reach zero, so shout 'Start'
				System.out.println("Start");
			}
			counter.countDown(); //count down by 1 for each second
			countVal = counter.getCount();
		}
	}

}
