package corepatterns.singleton;

import java.lang.reflect.Constructor;

public class DestroyReflectionSingletonTest {

	

	public static void main(String... args) 
	{
		
		EagerIntialization instanceOne = EagerIntialization.getInstance();
		EagerIntialization instanceTwo = null;
		
		Constructor[] constructors = EagerIntialization.class.getConstructors();
		
		for(Constructor constructor : constructors ) 
		{
			
			//this will break the singleton pattern
			constructor.setAccessible(Boolean.TRUE);
			try 
			{
				instanceTwo =  (EagerIntialization) constructor.newInstance();
			} catch (Exception e) 
			{
				e.printStackTrace();
		    }
			System.out.println(instanceOne.hashCode());
	        System.out.println(instanceTwo.hashCode());
	   }
	
   }
	
}
