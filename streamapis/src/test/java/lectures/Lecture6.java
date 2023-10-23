package lectures;


import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Test;

public class Lecture6 {

  final Predicate<Integer> numbersLessThan10 = n -> n > 5 && n < 10;

  @Test
  public void findAny() throws Exception {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int number = Arrays.stream(numbers).filter(n-> n <10)
    .findAny().orElse(-1);
    //optimized way
    int number1 = Arrays.stream(numbers).filter(numbersLessThan10)
    .findAny().orElse(-1);
    System.out.println("number::"+ number);

  }

  @Test
  public void findFirst() throws Exception {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int number = Arrays.stream(numbers).filter(n-> n <10)
    .findFirst().orElse(-1);
    //optimized way
    int number1 = Arrays.stream(numbers).filter(numbersLessThan10)
    .findFirst().orElse(-1);
    System.out.println("numberFirst::"+ number);
  }
}

