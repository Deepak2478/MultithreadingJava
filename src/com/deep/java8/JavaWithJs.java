package com.deep.java8;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaWithJs {
	public static Object processArbitraryJavaScript(
		      final String javaScriptCodeToProcess,
		      final String nameOfOutput,
		      final Map<String, Object> inputParameters)
		   {
		      final ScriptEngineManager manager = new ScriptEngineManager();
		      Object result = null;
		      final ScriptEngine engine = manager.getEngineByName("js");
		      try
		      {
		         if (inputParameters != null)
		         {
		            for (final Map.Entry<String,Object> parameter :
		                 inputParameters.entrySet())
		            {
		               engine.put(parameter.getKey(), parameter.getValue());
		            }
		         }
		         engine.eval(javaScriptCodeToProcess);
		         result = engine.get(nameOfOutput);
		      }
		      catch (ScriptException scriptException)
		      {
		        
		      }
		      return result;
		   }
	
	public static void main(String args[])
	{
		System.out.println("js executed code is "+processArbitraryJavaScript("var a = 'Tester'; b=a.substring(0,3);","b",null));
	}

}
