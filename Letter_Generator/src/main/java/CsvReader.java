import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Class for csvReader which handles csv file
 */
public class CsvReader {

  private Set<Map<String,String>> customersCollection;
  private List<String> HeaderList;
  private List<String> customerList;
  /**
   * representing zero for header line
   */
  public static final int zero = 0;

  /**
   * Method to read a file
   * @param file file
   * @return customersList
   */
  public List<String> readFile(String file) {

    //list to store all the lines of customers in string format
    List<String> customersList = new ArrayList<>();
    try (BufferedReader inputFile = new BufferedReader(
        new InputStreamReader(new FileInputStream(file), "UTF8"))) {

      String EachLine;

      while ((EachLine = inputFile.readLine()) != null) {
        customersList.add(EachLine);
      }
    } catch (FileNotFoundException fileNotFoundException) {
      System.out.println("File not found " + fileNotFoundException.getMessage());
    } catch (Exception e) {
      System.out.println("Exception Occurred: " + e.getMessage());
    }

    return customersList;

  }

  /**
   * Method to parse each line of csv file
   * @param line the line
   * @return the ParsedLineList
   */
  public List<String> parseEachLine(String line) {
    String[] stringArray = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);
    List<String> tempArray = new ArrayList<String>(Arrays.asList(stringArray));
    //remove quotes for each item
    List<String> parsedLine = new ArrayList<>();
    for (String s : tempArray) {
      String removedQuoteString = s.replace("\"", "");
      parsedLine.add(removedQuoteString);
    }
    return parsedLine;
  }

  /**
   * Method to get customers collection for placeholders
   * @param fileName filename
   * @return customersCollection
   */
  public Set<Map<String,String>> getCustomerCollection(String fileName){
    customerList = new ArrayList<>();
    customersCollection = new HashSet<>();
    List<String> csvContent = readFile(fileName);
    HeaderList = parseEachLine(csvContent.get(zero));
    for( int i = 1; i<csvContent.size(); i++){
      customerList = parseEachLine(csvContent.get(i));
      Map<String,String> customerFields = new HashMap<String,String>();
      if (customerList.size()>1){
        for(int j=0;j<HeaderList.size();j++){
          customerFields.put("[[" + HeaderList.get(j) + "]]",customerList.get(j));
        }
        customersCollection.add(customerFields);
      }



    }
    return customersCollection;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CsvReader csvReader = (CsvReader) o;
    return Objects.equals(customersCollection, csvReader.customersCollection)
        && Objects.equals(HeaderList, csvReader.HeaderList) && Objects.equals(
        customerList, csvReader.customerList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customersCollection, HeaderList, customerList);
  }

}
