package concurrentSolution;

import java.io.IOException;

/**
 * Main class to run concurrent solution program
 */
public class ConcurrentMain implements IntegerAndString {

  /**
   * SequentialMain function to call SummaryCSVCreator class and its method to run this project
   * @param args commandline arguments
   * @throws IOException Input and Output exception
   * @throws InterruptedException Interrupted Exception
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    if(args.length < ONE){
      throw new IllegalArgumentException("Invalid command line arguments!");
    }
    else{
      OutputDriver outputGenerator = new OutputDriver(args);
      outputGenerator.createSummaryCSV();
    }
  }
}
