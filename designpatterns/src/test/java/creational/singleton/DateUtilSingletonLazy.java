package creational.singleton;

public class DateUtilSingletonLazy {

	//Declare a private static member of the class
	private static DateUtilSingletonLazy instance;
	
	//Declare the constructor private
	private DateUtilSingletonLazy() {
	}
	
	//Declare a public static method which returns the instance of the class
	public static DateUtilSingletonLazy getInstance()
	{
		if(instance == null) { instance = new DateUtilSingletonLazy(); }
		return instance;
	}
}
