package com.deep.lambda;

import java.util.function.BiConsumer;

public class ExceptionHandlingExample {
	public static void main(String args[])
	{
		int[] someNumbers = {1,2,3,4};
		int key =2;
		
		process(someNumbers, key,(v,k) -> {
			try
			{
				System.out.println(v/k);
			}
			catch (ArithmeticException e) {
				System.out.println("Arithmetic exception happened");
			}
		});
		
		process(someNumbers, key, wrapperLambda((v,k) -> System.out.println(v/k)));
	}

	private static BiConsumer<Integer, Integer> wrapperLambda(BiConsumer<Integer, Integer> consumer) {
		return (v, k) -> {
			try{
				consumer.accept(v, k);
			}
			catch (ArithmeticException e) {
				System.out.println("Arithmetic exception in wrapper lambda");
			}
		};
	}

	private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer> consumer) {
		// TODO Auto-generated method stub
		for(int i: someNumbers)
		{
			consumer.accept(i, key);
		}
	}

}
