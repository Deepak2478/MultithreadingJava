package com.deep.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Illustrates how Callable, Executors, ExecutorService and Future are related
// also shows how they work together to execute a task
public class CallableTest {
	
	public static void main(String args[]) throws Exception
	{
		//the value for which we find the factorial
		long N = 20;
		//get a callable task to be submitted to executor service
		Callable<Long> task = new Factorial(N);
		
		//crate an executor service with a fixed thread pool consisting of one thread
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		//submit the task to executor service and store the future object
		Future<Long> future = es.submit(task);
		
		//wait for the get() method that blocks until computation is complete
		System.out.printf(" factorial of %d is %d", N, future.get());
		
		//done, shut down the executor service
		es.shutdown();
	}

}
