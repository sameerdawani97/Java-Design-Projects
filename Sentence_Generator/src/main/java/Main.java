import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 * base class for running the program through interface
 */
public class Main {

  /**
   * main function to call commandLineInterface class and its method to run project
   * @param args user input arguments
   * @throws IOException exception for input/output
   * @throws ParseException exception for input/output
   */
  public static void main(String[] args) throws IOException, ParseException{

    CommandLineInterface commandLineInterface = new CommandLineInterface();
    commandLineInterface.start();
  }
}

