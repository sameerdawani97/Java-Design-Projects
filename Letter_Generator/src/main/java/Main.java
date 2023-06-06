/**
 * Main class to run whole program
 */
public class Main {

  /**
   * Main method to run the code on terminal
   * @param args user input arguments
   * @throws Exception exception
   */
  public static void main(String[] args) throws Exception{
    CommandLineArgumentParser commandLineArgumentParser = new CommandLineArgumentParser();
    try{
      String nextInput = String.join(" ", args);
      boolean invalidInput = commandLineArgumentParser.checkValidCommandLineInput(nextInput);
      if (invalidInput == true) {
        System.out.println("Invalid Input");
        System.exit(0);
      } else {
        int zero = 0;
        int one = 1;
        int two = 2;
        int three = 3;

        String[] arguments = commandLineArgumentParser.getCommandlineInput(nextInput);
        Generator generator = new Generator();
        String mailType = arguments[zero];
        String template = arguments[one];
        String csvFile = arguments[two];
        String outDir = arguments[three];
        generator.generateFiles(template, outDir, csvFile);

      }
    }
    catch (Exception e) {
      throw new Exception(e);
    }


  }
}
