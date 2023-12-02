package lectures;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class Lecture3 {

  @Test
  public void min() throws Exception 
  {
    final List<Integer> numbers = ImmutableList.of(99, 2, 3, 100, 23, 93, 1);
    Integer min = numbers.stream().min(Comparator.naturalOrder()).get();
    assertThat(min).isEqualTo(1);
    System.out.println("min::"+ min);
    
    //sort elements in natural order
    List<Integer> sortedNatural = numbers.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
    IntStream.range(0, sortedNatural.size()).forEach(index ->
    {
    System.out.print(sortedNatural.get(index));
    });
    
    System.out.println();
    
    //sort elements in reverse order
    List<Integer> sortedReverse = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    IntStream.range(0, sortedReverse.size()).forEach(index ->
    {
    System.out.print(sortedReverse.get(index));
    });
  }

  @Test
  public void max() throws Exception {
    final List<Integer> numbers = ImmutableList.of(1, 2, 3, 100, 23, 93, 99);
    Integer max = numbers.stream().max(Comparator.naturalOrder()).get();
    assertThat(max).isEqualTo(100);
    System.out.println("max::"+ max);
  }
}
