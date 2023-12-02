package corepatterns.singleton;

public class TestSingleton
{

	public static void main(String[] args)
	{
		DateUtilSingletonLazy dateUtil1 = DateUtilSingletonLazy.getInstance();
		DateUtilSingletonLazy dateUtil12 = DateUtilSingletonLazy.getInstance();
		System.out.println(dateUtil1 == dateUtil12);
		
		DateUtilSingletonThreadSafe dateUtil3 = DateUtilSingletonThreadSafe.getInstance();
		DateUtilSingletonThreadSafe dateUtil14 = DateUtilSingletonThreadSafe.getInstance();
		System.out.println(dateUtil3 == dateUtil14);
	}

}
