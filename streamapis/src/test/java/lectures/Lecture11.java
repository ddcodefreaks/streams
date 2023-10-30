package lectures;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture11 {

  @Test
  public void joiningStrings() throws Exception {
    List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");
   String   joinedName = "" ;
    for(String name : names)
    {
    	joinedName = joinedName + ", " + name;
    }
    //removing first comma
    joinedName = joinedName.substring(2, joinedName.length()-1);
    System.out.println(joinedName);
    
  }

  @Test
  public void joiningStringsWithStream() throws Exception
  {
    List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");
    //using join
    String join = names.stream().map(String :: toUpperCase).collect(Collectors.joining("|"));
    System.out.println(join);
  }
}
