import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import org.json.simple.parser.ParseException;


/**
 * This class is for managing command line inputs
 */
public class CommandLineInterface{
  private String directoryPath;
  private JsonParser jsonParser;
  private List<String> GrammarTitlesList;

  /**
   * This is string for informing to user about invalid input
   */
  public static final String INVALID_INPUT = "Invalid input, please input y/n";
  /**
   * this is string for informing to user that if he would like another.
   */
  public static final String ANOTHER_REQUEST = "Would you like another? (y/n)";
  /**
   * This is string for informing to user that select in range
   */
  public static final String CHOOSE_RANGE = "Invalid input, choose from options between 1 - ";
  /**
   * variable to represent 1 constant
   */
  public static final int ONE = 1;
  /**
   * variable to represent 0 constant
   */
  public static final int ZERO = 0;

  /**
   * constructor
   * @throws IOException input or output exception
   * @throws ParseException parseException
   */
  public CommandLineInterface() throws IOException, ParseException{
    this.directoryPath=getFileReader();
    jsonParser = new JsonParser(this.directoryPath);
    GrammarTitlesList = jsonParser.getGrammarTitles();

  }

  /**
   * This method is the basis for whole functionality called by main class. Driver function
   * @throws IOException exception for input/output
   * @throws ParseException exception for input/output
   */
  public void start() throws IOException, ParseException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Loading grammars...");
    String input = "";
    while (!input.equals("q")) {
      System.out.println("The Following grammars are available:");
      showGrammarList();
      System.out.println("What would you like to use? (q to quit)");
      input = sc.nextLine();

      if (input.equals("q")) {
        System.out.println("Exit");
        break;
      }
      String yesNo = "y";
      sentenceGenerate(yesNo, input, sc);
    }

  }

  /**
   * This method is responsible for sentence generation
   * @param yesNo yesNo input by user
   * @param input input by user
   * @param sc scanner object
   */
    private void sentenceGenerate(String yesNo, String input, Scanner sc) throws IOException, ParseException {
      while (yesNo.equals("y")) {
        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > GrammarTitlesList.size()) {
          System.out.println(CHOOSE_RANGE + (GrammarTitlesList.size()));
          break;
        } else {
          Grammar grammar = jsonParser.getGrammarByUserInput(Integer.parseInt(input) - ONE);
          SentenceGenerator sentenceGenerator = new SentenceGenerator(grammar);
//          System.out.println("sameer");
          System.out.println(sentenceGenerator.generateSentence());
        }
        System.out.println(ANOTHER_REQUEST);
        yesNo = sc.nextLine();
        if (!((yesNo.equals("y") || (yesNo.equals("n"))))) {
          System.out.println(INVALID_INPUT);
          while (!yesNo.equals("y") && !yesNo.equals("n")) {
            yesNo = sc.nextLine();
            if (!yesNo.equals("y") && !yesNo.equals("n")) {
              System.out.println(INVALID_INPUT);
            }

          }
        }
      }
    }

  /**
   * This method shows the grammar list available
   */
  private void showGrammarList(){
    int counter = ONE;
    for (int i = ZERO; i < GrammarTitlesList.size(); i++) {

      System.out.println(counter + ". " + GrammarTitlesList.get(i));
      counter = counter + ONE;

    }

  }

  /**
   * This method returns the directory path after selection from user dialog box
   * @return directory path
   * @throws IOException if file is unknown
   */
  private String getFileReader() throws IOException {
    String directoryPath="" ;
    File curFile = new File(new File(".").getCanonicalPath());
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(curFile);
    fileChooser.setDialogTitle("Choose the directory for grammar files");
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int result = fileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {

      File file = fileChooser.getSelectedFile();

      try {

        if (file != null) {

          directoryPath = file.getCanonicalPath();

        }

      } catch (IOException e) {

      }

    }
    return directoryPath;
  }

}
