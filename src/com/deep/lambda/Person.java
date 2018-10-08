package com.deep.lambda;

public class Person {

	private String firstName;
	private String lastName;
	private String age;
	
	public Person(String firstName, String lastName, String age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString()
	{
		return "Person[firstName :"+firstName+" lastName : "+lastName+" age:"+age;
	}
}
