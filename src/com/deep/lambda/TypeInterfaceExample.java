package com.deep.lambda;

public class TypeInterfaceExample {
	
	public static void main(String args[])
	{
		StringLengthLambda myLambda = (String s) -> s.length();
		myLambda.getLength("hello lambda");
		
	}

}
interface StringLengthLambda{
	int getLength(String s);
}