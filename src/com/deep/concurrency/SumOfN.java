package com.deep.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// this class finds out sigma n
// We divide the class
// to sum the no. to 10 threads
// once computation is complete, we add the results of all threads
// and check if the calculation is correct by using the formula (N)(N+1)/2
public class SumOfN {

	private static long N = 1_000_000L; // 1 million
	private static long calculateSum =0; //value to hold the sum of values in the range 1...N
	private static final int NUM_THREADS = 10; //no. of threads
	
	//this is a callable object that sum's the no's in a specified range
	static class SumCalc implements Callable<Long>
	{

		long from, to, localSum=0;
		
		public SumCalc(long from, long to)
		{
			this.from = from;
			this.to  =to;
		}
		
		// add the no's in the specified range
		@Override
		public Long call() throws Exception {
			for(long i= from; i<=to; i++)
			{
				localSum += i;
			}
			return localSum;
		}
		
	}
	
	
	// in main method, we divide the summation task to threads and finally check
	// if the sum found out is correct
	
	public static void main(String args[])
	{
		// divide the task among fixed no. of threads
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
		//store the references to the future objects in a list for summing up together
		List<Future<Long>> summationTasks = new ArrayList<Future<Long>>();
		long nByTen = N/10;
		for(int  i =0; i< NUM_THREADS; i++)
		{
			//create a summation task
			//starting from (10*0 +1 ..(N/10*1) to (10*9)+1 ...)
			long fromInnerRange = nByTen*i +1;
			long toInnerRange = nByTen*(i+1);
			System.out.printf("Spawning thread in the range %d to %d %n",fromInnerRange,toInnerRange);
			
			// create a callable object for given summation range
			Callable<Long> summationTask = new SumCalc(fromInnerRange, toInnerRange);
			
			// submit the task to the executor service
			Future<Long> futureSum = executorService.submit(summationTask);
			
			//it will tale some time to commit soadd it tho the list to revisit later
			summationTasks.add(futureSum);
		}
		
		// calculate the sum of each task
		for(Future<Long> partiallSum : summationTasks)
		{
			try
			{
				calculateSum += partiallSum.get();
			}
			catch(CancellationException| ExecutionException
					| InterruptedException exception)
			{
				exception.printStackTrace();
				System.exit(-1);
			}
			
		}
		// now calculate the sum using formula and compare the two results
		long formulaSum = (N)*(N+1)/2;
		System.out.printf("Sum by threads = %d , Sum by formula = %d ",calculateSum,formulaSum);		
	}
}
