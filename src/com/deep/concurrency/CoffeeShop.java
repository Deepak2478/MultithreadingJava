package com.deep.concurrency;

import java.util.concurrent.Exchanger;

public class CoffeeShop extends Thread {

	private Exchanger<String> sillyTalk;
	
	public CoffeeShop(Exchanger<String> sillyTalk)
	{
		this.sillyTalk = sillyTalk;
	}
	
	public void run()
	{
		String reply = null;
		try
		{
			reply = sillyTalk.exchange("Who's there");
			System.out.println("Duke: "+reply);
			
			reply = sillyTalk.exchange("Duke who?");
			System.out.println("Duke:" +reply);
			
			reply = sillyTalk.exchange(" ");
			System.out.println("Duke:" +reply);
		}
		catch(InterruptedException ie)
		{
			System.out.println("ie excepotion in coffee shop");
		}
	}
}
