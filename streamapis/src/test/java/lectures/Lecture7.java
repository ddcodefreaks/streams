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
  public void min() throws Exception {

  }

  @Test
  public void max() throws Exception {

  }


  @Test
  public void average() throws Exception {
    List<Car> cars = MockData.getCars();

  }

  @Test
  public void sum() throws Exception {
    List<Car> cars = MockData.getCars();
    double sum = cars.stream()
        .mapToDouble(Car::getPrice)
        .sum();
    BigDecimal bigDecimalSum = BigDecimal.valueOf(sum);
    System.out.println(sum);
    System.out.println(bigDecimalSum);

  }

  @Test
  public void statistics() throws Exception {
    List<Car> cars = MockData.getCars();
    DoubleSummaryStatistics statistics = cars.stream()
        .mapToDouble(Car::getPrice)
        .summaryStatistics();
    System.out.println(statistics);
    System.out.println(statistics.getAverage());
    System.out.println(statistics.getCount());
    System.out.println(statistics.getMax());
    System.out.println(statistics.getMin());
    System.out.println(statistics.getSum());
  }

}