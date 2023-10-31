package lectures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import beans.Person;
import mockdata.MockData;


public class Lecture1 {

  @Test
  public void imperativeApproach() throws IOException {
    List<Person> people = MockData.getPeople();
    // 1. Find people aged less or equal 18
    // 2. Then change implementation to find first 10 people
    List<Person> first10People = new ArrayList<>();
    for(Person person : people)
    {
    	if(person.getAge()<=18)
    	{
    		if(first10People.size()<10)
    		{
    		first10People.add(person);
    		}
    	}
    }
    System.out.println("List of first10People::"+ first10People);
    //first10People.forEach(System.out:: println);
  }

  @Test
  public void declarativeApproachUsingStreams() throws Exception {
    ImmutableList<Person> people = MockData.getPeople();
    List<Person> first10People = people.stream().filter(obj-> obj.getAge()<=18).limit(10).collect(Collectors.toList());
    first10People.forEach(System.out:: println);
    //shorter Style one line code
    MockData.getPeople().stream().filter(obj-> obj.getAge()<=18).limit(10).collect(Collectors.toList()).forEach(System.out:: println);

  }
}
