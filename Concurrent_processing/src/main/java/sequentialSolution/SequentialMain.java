package sequentialSolution;

import static sequentialSolution.StudentParser.*;

import java.io.IOException;

/**
 * Main class to run the sequential solution program
 */
public class SequentialMain {

  /**
   * SequentialMain function to call SummaryCSVCreator class and its method to run this project
   * @param args - The name of directory that will be passed by the commandline arguments
   * @throws IOException - Input/Output exception
   * @throws FileNotFoundException File is not found
   */
  public static void main(String[] args) throws IOException, FileNotFoundException {
      SummaryCSVCreator summaryCSVCreator = new SummaryCSVCreator();
      summaryCSVCreator.createSummaryCSV(args[ZEROINDEX]);
  }
}
