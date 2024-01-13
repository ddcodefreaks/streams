/**
 * 
 */
package creational.singleton;

/**
 * 
 */
public class ThreadSafeSingletonDoubleLocking {

	/**
	 * private
	 */
	private ThreadSafeSingletonDoubleLocking() {}
	
	
	private static ThreadSafeSingletonDoubleLocking instance;
	
	public ThreadSafeSingletonDoubleLocking getInstance()
	{
		
		if(instance == null) {
			synchronized(ThreadSafeSingleton.class) 
			{
				if(instance == null) 
				{
					instance = new ThreadSafeSingletonDoubleLocking();
				}
			}
		}
		
		return instance;
	}
}

