package com.deep.concurrency;

import java.util.concurrent.Exchanger;

public class KnockKnock {
	public static void main(String args[])
	{
		Exchanger<String> sillyTalk = new Exchanger<String>();
		new CoffeeShop(sillyTalk).start();
		new DukeThread(sillyTalk).start();
	}
}
