package com.deep.lambda;

public class Greeter {
	public void greet(Greeting greeting)
	{
		greeting.perform();
	}
	
	public static void main(String args[])
	{
		
		
		MyLambda myLambdaFunction = () -> System.out.println("Hello World 2");
		myLambdaFunction.printGreeting();
		MyAdd myAddExpr = (a,b) -> a+b; 
		myAddExpr.foo(1, 5);
	}

}
interface MyLambda{
	void printGreeting();
}
interface MyAdd{
	int foo(int a, int b);
}