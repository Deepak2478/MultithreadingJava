package com.deep.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Unit1Exercise {

	public static void main(String[] args) {
		List<Person> people = Arrays.asList(
		new Person("test1", "rest1", "1"),
		new Person("test2", "cest2", "5"),
		new Person("test3", "yest3", "3"),
		new Person("test4", "qest4", "7")
		);
		
		//old way
		Collections.sort(people, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2)
			{
				return p1.getLastName().compareTo(p2.getLastName());
			}
		});
		
		//lambda way
		Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
		
		
		
		printConditional(people, (p) -> p.getLastName().startsWith("C"));
		
		/*printConditional(people, new Conditional() {
			
			@Override
			public boolean test(Person p) {
				// TODO Auto-generated method stub
				return p.getLastName().startsWith("C") ;
			}
		});*/
		
		// new way of running for loop
		people.forEach(p -> System.out.println(p));
		//or
		people.forEach(System.out::println);
		//streams
		long count = people.stream().filter(p -> p.getFirstName().startsWith("C")).count();
	}

	private static void printConditional(List<Person> people, Predicate<Person> predicate) {
		// TODO Auto-generated method stub
		for(Person p : people)
		{
			if(predicate.test(p))
			{
				System.out.println(p.toString());
			}
		}
		
	}

}

interface Conditional
{
	boolean test(Person p);
}