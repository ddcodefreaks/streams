package creational.singleton;

public class EagerIntialization {
	
	
	//create a private constructor 
	private EagerIntialization() { }
	
	//create private static final instance variable of the same class
	private static final EagerIntialization instance = new EagerIntialization();
	
	// create public static method which return the return the instance 
	
	public static EagerIntialization getInstance() {
		return instance;
	}
}
