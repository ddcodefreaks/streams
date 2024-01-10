package corepatterns.singleton;

public class LazyInitializedSingleton {

	
	// create constructor private 
	private LazyInitializedSingleton() { }
	
	// variable 
	public static LazyInitializedSingleton instance ;
	
	public LazyInitializedSingleton getInstance() 
	{
		
		if(null == instance) {
			instance = new LazyInitializedSingleton();
		}
		
		return instance;
	}
}
