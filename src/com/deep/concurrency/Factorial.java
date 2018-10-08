package com.deep.concurrency;

import java.util.concurrent.Callable;

// Factorial implements Callable so it can be passed to an ExecutorService
//and get executed task
class Factorial implements Callable<Long> {

	long n;
	public Factorial(long n){
		this.n = n;
	}
	
	public Long call() throws Exception
	{
		if(n <= 0)
		{
			throw new Exception(" n should be greater than zero");
		}
		long fact = 1;
		for(long longVal = 1; longVal <= n; longVal++)
		{
			fact *= longVal;
		}
		return fact;
	}
}
