package com.deep.concurrency;

import java.util.concurrent.Phaser;

// The work could be a cook, helper or attendant. Though the three
//work independently, they should all synchronize their work together
// to complete preparing the food item
public class Worker extends Thread {
	
	Phaser deliveryOrder;
	Worker(Phaser order, String name)
	{
		deliveryOrder = order;
		this.setName(name);
		deliveryOrder.register();
		start();
	}
	public void run()
	{
		for(int i=1; i<=3; i++)
		{
			System.out.println("\t"+getName()+"doing this work for order no. "+i);
			if(i == 3)
			{
				//work completed for this delivery order, so deregister
				deliveryOrder.arriveAndDeregister();
			}
			else
			{
				deliveryOrder.arriveAndAwaitAdvance();
			}
			try{
				Thread.sleep(3000);//simulate time for preparing food item
			}catch(InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
}
