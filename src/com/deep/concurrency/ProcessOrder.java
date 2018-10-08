package com.deep.concurrency;

import java.util.concurrent.Phaser;

// Process order thread is the master thread overlooking to make sure
// the cook, helper and attendant are doing their part of work to complete
//preparing food items and complete order delivery
//To simplify the logic, we assume each delivery consists 3 food items
public class ProcessOrder {

	public static void main(String args[])
	{
		//Phaser is a synchronizer to make food items one by one
		Phaser deliveryOrder = new Phaser(1);
		System.out.println("Starting to process order delivery");
		
		new Worker(deliveryOrder, "Cook");
		new Worker(deliveryOrder, "Helper");
		new Worker(deliveryOrder, "Attendant");
		
		for(int i = 1; i<= 3; i++)
		{
			//prepare, mix and deliver food item
			deliveryOrder.arriveAndAwaitAdvance();
			System.out.println("Deliver food item no. :"+i);
		}
		//work completed for this delivery order, so deregister
		deliveryOrder.arriveAndDeregister();
		System.out.println("Delivery order completed...give it to customer");
	}
}
