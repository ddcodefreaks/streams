package lectures;


import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import beans.Car;
import beans.Person;
import mockdata.MockData;

public class Lecture5 {

  @Test
  public void understandingFilter() throws Exception {
    ImmutableList<Car> cars = MockData.getCars();
    //filter cars based on the  price  less than  1000 
    List<Car> filteredCars = cars.stream().filter(car-> car.getPrice()<10000)
    .collect(Collectors.toList());
    filteredCars.forEach(System.out::println);

    // filter car based on price but use predicate 
    Predicate<Car>  predicateCar =  car-> car.getPrice() <20000 ;
    List<Car> filteredCarswithPred = cars.stream().filter(predicateCar)
    .collect(Collectors.toList());
    filteredCarswithPred.forEach(System.out::println);
    

  }

  @Test
  public void ourFirstMapping() throws Exception {
    // transform from one data type to another
    List<Person> people = MockData.getPeople();

  }

  @Test
  public void averageCarPrice() throws Exception {
    // calculate average of car prices

  }

  @Test
  public void test() throws Exception {

  }
}



