package concurrentSolution;

import java.util.Arrays;
import java.util.List;

/**
 * Interface for static integers and strings
 */
public interface IntegerAndString {

  /**
   * Integer 0
   */
  Integer ZERO = 0;
  /**
   * Integer 1
   */
  Integer ONE = 1;
  /**
   * Integer 2
   */
  Integer TWO = 2;
  /**
   * Integer 3
   */
  Integer THREE = 3;
  /**
   * Concurrent file writer threads active
   */
  Integer NUMBER_OF_FILE_WRITER_THREADS = 3;
  /**
   * Integer 4
   */
  Integer FOUR = 4;
  /**
   * Integer 5
   */
  Integer FIVE = 5;
  /**
   * Integer 9
   */
  Integer BUFFER_SIZE = 9;
  
  /**
   * output directory
   */
  String OUTPUT_DIRECTORY= "src/main/resources/concurrent_files/";
  /**
   * csv file extension
   */
  String CSV_EXTENSION = ".csv";
  /**
   * CSV file separator
   */
  String SEPARATOR = ",";
  /**
   * file name separator
   */
  String FILE_NAME_SEPARATOR = "_";

  /**
   *  labels of output csv files
   */
  List<String> LABELS = Arrays.asList("date", "total_clicks");

}
