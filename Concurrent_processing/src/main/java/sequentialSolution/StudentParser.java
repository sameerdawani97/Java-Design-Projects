package sequentialSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;


/**
 * Class to parse student csv file
 */
public class StudentParser {
  private String fileName;
  /**
   * to split the line by ','
   */
  public static String splitBy = ",";
  /**
   * represent 0 index
   */
  public static Integer ZEROINDEX = 0;
  /**
   * represent 1 index
   */
  public static Integer ONEINDEX = 1;
  /**
   * represent 4 index
   */
  public static Integer FOURINDEX = 4;
  /**
   * represent 5 index
   */
  public static Integer FIVEINDEX = 5;

  /**
   * Constructor for Student parser
   * @param fileName filename of students
   */
  public StudentParser(String fileName){
    this.fileName=fileName;
  }

  /**
   * This method parses the student csv file and stores it into hashmap containing another hashmap of files info to be created
   * @param storageMap storageMap containing hashmap values empty
   * @return storageMap is filled with information taken by student csv
   * @throws IOException IOException
   */
  public HashMap<String,HashMap<String,Integer>> parseStudent(HashMap<String,HashMap<String,Integer>> storageMap) throws IOException {
    String line = "";
    FileReader fileReader = new FileReader(Paths.get(fileName).toFile());
    BufferedReader br = new BufferedReader(fileReader);
    line = br.readLine();
    String[] studentHeaders = line.split(splitBy);
    for (int i = 0; i<studentHeaders.length; i++){
      studentHeaders[i] = studentHeaders[i].replace("\"","");
    }
    while ((line = br.readLine())!=null){
      String[] studentRecord = line.split(splitBy);
      for (int i = 0; i<studentRecord.length; i++){
        studentRecord[i] = studentRecord[i].replace("\"","");
      }
      if (storageMap.containsKey(studentRecord[ZEROINDEX]+"_"+studentRecord[ONEINDEX])){
        HashMap<String,Integer> temp = storageMap.get(studentRecord[ZEROINDEX]+"_"+studentRecord[ONEINDEX]);
        // If there is already record for particular date then add with existing record
        if(temp.containsKey(studentRecord[FOURINDEX])){
          temp.put(studentRecord[FOURINDEX],temp.get(studentRecord[FOURINDEX])+Integer.parseInt(studentRecord[FIVEINDEX]));
        }
        //else put a record for particular date
        else{
          temp.put(studentRecord[FOURINDEX],Integer.parseInt(studentRecord[FIVEINDEX]));
        }
      }
    }
    return storageMap;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentParser that = (StudentParser) o;
    return Objects.equals(fileName, that.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName);
  }

  @Override
  public String toString() {
    return "StudentParser{" +
        "fileName='" + fileName + '\'' +
        '}';
  }
}
