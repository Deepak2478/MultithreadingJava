package com.deep.java8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Identifier {

	public static void main(String[] args) throws IOException {
		//Simple try-catch-finally
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("C:/myFile.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			fileInputStream.close();
		}
		
		
		
		
		
		
		
		//try with resources
		try (FileInputStream fileInputStream2 = new FileInputStream("C:/myFile.txt")) 
		{
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
