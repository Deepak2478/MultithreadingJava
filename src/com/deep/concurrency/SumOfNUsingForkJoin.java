package com.deep.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// illustrates how to compute sum for 1toN using fork join
// the range of numbers are divided into half until the range can be handled by a thread
// once the range summation completes, the result gets summed together
public class SumOfNUsingForkJoin {

	private static long N = 1000_000;
	private static final int NUM_THREADS = 10; //no. of threads to create for distibuting the effort
	
	//this is the recursive implementation of the algorithm; inherit from 
	//RecursiveTask instead of RecursiveAction since we are returning values.
	static class RecursiveSumOfN extends RecursiveTask<Long>
	{
		long from,to;
		//from and to are range of values to sum-up
		public RecursiveSumOfN(long from, long to) {
			this.from = from;
			this.to = to;
		}
		//method performs fork and join to compute the sum
		//if the range of values can be summed by a thread then,
		//then sum the range of numbers otherwise fork the range and join the results
		@Override
		protected Long compute() {
			if((from - to) <= N/NUM_THREADS)
			{
				//the range is something that can be handled by a thread so do summation
				long localSum=0;
				//add in range 'from' .. 'to' inclusive of value 'to'
				for(long i = from; i<=to ; i++)
				{
					localSum += i;
				}
				System.out.printf("\t summing of value range %d to %d is %d to %n", from,to,localSum);
				return localSum;
			}
			else
			{
				// the range is big for a thread to handle so fork the computation
				// we find the mid point value in the range from..to
				long mid = (from+to)/2;
				System.out.printf("Forking the computation into two ranges: "+
						"%d to %d and %d to %d %n",from, mid,  mid,to);
				//determine the computation of the first half of the range ie. from...mid
				RecursiveSumOfN firstHalf = new RecursiveSumOfN(from,mid);
				//now fork that task
				firstHalf.fork();
				//determine the computation of the second half of the range ie. mid+1...to
				RecursiveSumOfN secondHalf = new RecursiveSumOfN(mid+1,to);
				long secondResult = secondHalf.compute();
				//now wait for first half to compute sum
				//complete, once done, add it to remaining part
				return firstHalf.join()+ secondResult;
				
			}
		}
		
	}
	
	public static void main(String args[])
	{
		// create fork join pool that consists of NUM_THREADS
		ForkJoinPool pool = new ForkJoinPool(NUM_THREADS);
		// submits the computation task to fork join pool
		long computedSum = pool.invoke(new RecursiveSumOfN(0, N));
		long formuldSum = (N)*(N+1)/2;
		System.out.printf("Sum for range 1...%d; compute sum = %d, formula sum"
				+ "= %d %n", N, computedSum, formuldSum);
	}
}
