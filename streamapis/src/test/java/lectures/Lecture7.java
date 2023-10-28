package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import beans.Person;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture7 {

  @Test
  public void count() throws Exception {
	  //count number of females from person
	  List<Person> persons = MockData.getPeople();
	  //long femaleCount = persons.stream().filter(person-> person.getGender().equalsIgnoreCase("Female")).count();
	  //System.out.println("femaleCount::"+ femaleCount);
	  
	  //use predicate 
	  Predicate<Person> checkFemale = person-> person.getGender().equalsIgnoreCase("Female");
	  long femaleCount1 = persons.stream().filter(checkFemale).count();
	  System.out.println("femaleCount1::"+ femaleCount1);
	  

  }

  @Test
  public void min() throws Exception 
  {
	  // find min without using comparator
	  // find out minimum price  of the car which is yellow color
	  List<Car> cars = MockData.getCars();
	  Predicate<Car> yellowCars = car-> car.getColor().equalsIgnoreCase("Yellow");
	  double min = cars.stream().filter(yellowCars)
	  .mapToDouble(Car :: getPrice)
	  .min().getAsDouble();
	  System.out.println("minimum price is::"+ min);
  }

  @Test
  public void max() throws Exception 
  {
	  	  // find max without using comparator
		  // find out maximum price  of the car which is yellow color
		  List<Car> cars = MockData.getCars();
		  Predicate<Car> yellowCars = car-> car.getColor().equalsIgnoreCase("Yellow");
		  double max = cars.stream().filter(yellowCars)
		  .mapToDouble(Car :: getPrice)
		  .max().getAsDouble();
		  System.out.println("minimum price is::"+ max);
  }


  @Test
  public void average() throws Exception 
  {
    List<Car> cars = MockData.getCars();
    //calculate the average of all the prices for the car
     double averagePrice = cars.stream()
    		 .mapToDouble(Car :: getPrice)
    		 .average().orElse(0);
     System.out.println("The average of the car prices is::"+ averagePrice);

  }

  @Test
  public void sum() throws Exception 
  {
    List<Car> cars = MockData.getCars();
    
    //calculate the sum of all car prices
    double sumOfPrices = cars.stream().mapToDouble(Car :: getPrice).sum();
    System.out.println("The sum of all the prices::"+ sumOfPrices);
    
    //readable format
    BigDecimal bigDecimal = BigDecimal.valueOf(sumOfPrices);
    System.out.println("Big decimal sum of prices::"+ bigDecimal);
  }

  @Test
  public void statistics() throws Exception {
    List<Car> cars = MockData.getCars();
    
    //get the statistics on price of the car
    DoubleSummaryStatistics statitics = cars.stream().mapToDouble(Car :: getPrice).summaryStatistics();
    System.out.println("Statistics::"+ statitics);
    //get the component from the statistics
    System.out.println("Statistics::"+ statitics.getCount());
    System.out.println("Statistics::"+ statitics.getAverage());
    System.out.println("Statistics::"+ statitics.getMax());
    System.out.println("Statistics::"+ statitics.getMin());
    System.out.println("Statistics::"+ BigDecimal.valueOf(statitics.getSum()));
  }

}