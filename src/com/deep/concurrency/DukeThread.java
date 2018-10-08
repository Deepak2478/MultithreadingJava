package com.deep.concurrency;

import java.util.concurrent.Exchanger;

//Its an independent thread. It talks to coffeeShop thread which is also 
//an independent thread. Chat is achieved by exchanging message through 
// a common Exchanger<String> object that synchronizes chat between them.
//messages printed are responses from coffeeShop thread.
public class DukeThread extends Thread {
	private Exchanger<String> sillyTalk;
	
	public DukeThread(Exchanger<String> args)
	{
		sillyTalk = args;
	}
	public void run()
	{
		String reply = null;
		try{
			// start conversation with coffee shop thread
			reply = sillyTalk.exchange("knock knock");
			// print response received from coffee shop thread
			System.out.println("Coffee shop : "+reply);
			
			
			// exchange another set of messages
			reply = sillyTalk.exchange("duke");
			// print response received from coffee shop thread
			System.out.println("Coffee shop : "+reply);
			
			// an exchange can happen when both send and receive happens
			//Since this is the last sentence to speak, we close the chat by ignoring dummy reply
			reply = sillyTalk.exchange("last message");
		}
		catch(InterruptedException ie)
		{
			System.err.println("ie exception in silly talk");
		}
	}
}
