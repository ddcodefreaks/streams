package lectures;


import beans.Car;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.assertj.core.util.Lists;
import org.junit.Test;

public class Lecture8 {

  @Test
  public void simpleGrouping() throws Exception 
  {
	// group the cars by its manufactures
    Map<String , List<Car>> groupCars = MockData.getCars().stream().collect(Collectors.groupingBy(Car :: getMake));
    // print the output
    groupCars.forEach((make, cars)-> 
    {
    	System.out.println(make);
    	cars.forEach(System.out :: println);
    });
  }

  @Test
  public void groupingAndCounting() throws Exception {
    ArrayList<String> names = Lists
        .newArrayList(
            "John",
            "John",
            "Mariam",
            "Alex",
            "Mohammado",
            "Mohammado",
            "Vincent",
            "Alex",
            "Alex"
        );
    
    //count how many times names got repeated and print
    Map<String , Long> counting = names.stream()
    		.collect(Collectors.groupingBy(Function.identity() , Collectors.counting()));
    //print the output
    counting.forEach((name , count) -> System.out.println(name + "::" + count));
  }
  
  //given an array of integers you need to count the occurrences of each element in the array
  @Test
  public void countElementsInArray()
  {
	  int[] arr = new int[]{1,2,2,2,1,1,4,5,6,3,3,7,7} ;
	  
	  Map<Integer, Long> countElement = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	  //printing the output
	  countElement.forEach((number , count)-> System.out.println(number+ "::"+ count));
  }

}