package com.deep.lambda;

public class MethodReferenceExample {

	public static void main(String args[])
	{
		Thread t = new Thread(() -> printMessage());
		//or
		Thread t1 = new Thread(MethodReferenceExample :: printMessage);
	}
	public static void printMessage()
	{
		System.out.println("Hello World");
	}
}
