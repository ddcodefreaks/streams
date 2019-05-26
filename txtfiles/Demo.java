abstract class m1{

abstract void show1();

}

abstract class m2 {
abstract void show2();
}

abstract class m3   {
 abstract void show3();
}

public class Demo extends m3
{

 void show3(){
 System.out.println("testing");
 }
 
 

public static void main(String... args){
  new Demo().show3();
}
}