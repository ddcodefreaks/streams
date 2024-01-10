package corepatterns.singleton;

public class ThreadSafeSingleton {
	
	
	private ThreadSafeSingleton() { }
	
	private static ThreadSafeSingleton instance;
	
	public synchronized ThreadSafeSingleton getInstance() {
		
		if(instance == null) {
			instance = new ThreadSafeSingleton();
		}
		
		return instance;
	}
}
