package sequentialSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class parse the course file
 */
public class CourseParser {

  private String fileName;

  /**
   * splitBy notation
   */
  public static String splitBy = ",";

  /**
   * Constructor for Course parser
   * @param fileName filename of courses
   */
  public CourseParser(String fileName){
    this.fileName = fileName;
  }

  /**
   * this method parse the course file
   * @return course file parsed in hashmap
   * @throws IOException IOException
   */
  public HashMap<String, ArrayList<String>> parseCourse() throws IOException{
    HashMap<String, ArrayList<String>> parsedCsv = new HashMap<>();
    String line = "";
    FileReader fileReader = new FileReader(Paths.get(fileName).toFile());
    BufferedReader br = new BufferedReader(fileReader);
    line = br.readLine();
    String[] headers = line.split(splitBy);

    for (int i = 0; i< headers.length; i++){
      headers[i] = headers[i].replace("\"","");
    }

    // creating arraylist for each course attribute with key as its header column
    for (int i =0; i<headers.length; i++){
      parsedCsv.put(headers[i], new ArrayList<String>());
    }

    // storing each course to its corresponding header value in hashmap
    while ((line = br.readLine())!=null){
      String[] values = line.split(splitBy);
      for(int i=0; i<values.length;i++){
        values[i] = values[i].replace("\"","");
        parsedCsv.get(headers[i]).add(values[i]);
      }
    }
    return parsedCsv;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CourseParser that = (CourseParser) o;
    return Objects.equals(fileName, that.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName);
  }

  @Override
  public String toString() {
    return "CourseParser{" +
        "fileName='" + fileName + '\'' +
        '}';
  }
}
