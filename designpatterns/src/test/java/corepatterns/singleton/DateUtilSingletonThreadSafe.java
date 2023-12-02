package corepatterns.singleton;

public class DateUtilSingletonThreadSafe 
{

		//Declare a private static volatile member of the class
		private static volatile DateUtilSingletonThreadSafe instance;
		
		//Declare the constructor private
		private DateUtilSingletonThreadSafe() {
		}
		
		//Declare a public static method which returns the instance of the class
		public static DateUtilSingletonThreadSafe getInstance()
		{
			if(instance == null) 
			{
			//acquire class level lock by using synchronized block
			synchronized(DateUtilSingletonThreadSafe.class)
			{
			if(instance == null) { instance = new DateUtilSingletonThreadSafe(); }
			}
			
			}
			return instance;
		}
}
