import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for command line arguments
 */
public class CommandLineArgumentParser {

  private String[] arguments;
  private String workableArguments[];
  /**
   * argument index number zero
   */
  public static final int zero = 0;
  /**
   * argument index number one
   */
  public static final int  one = 1;
  /**
   * argument index number two
   */
  public static final int  two = 2;
  /**
   * argument index number three
   */
  public static final int  three = 3;
  /**
   * length of workable arguments
   */
  public static final int len = 4;

  /**
   * Method to validate user input
   * @param input user input as string
   * @return boolean value to validate user input
   */
  public boolean checkValidCommandLineInput(String input) {
    String email = "--email";
    String emailTemplate = "--email-template";
    String letter = "--letter";
    String letterTemplate = "--letter-template";
    String outputDir = "--output-dir";
    String csvFile = "--csv-file";

    HashMap<String, String> checkMailType = new HashMap<>();
    checkMailType.put(email, emailTemplate);
    checkMailType.put(letter, letterTemplate);


    boolean invalidInput = false;



    if (!(input).contains(letter) && !(input).contains(email)) {
      System.out.println("Not found " + email + " or " + letter + " ");
      invalidInput = true;
    }
    if (!(input).contains(outputDir)) {
      System.out.println("Not found " + outputDir);
      invalidInput = true;
    }

    if (input.contains(email) && input.contains(letter)) {
      System.out.println("Error: Conflicting command line argument was provided");
      invalidInput = true;
    }

    if (!(input).contains(csvFile)) {
      System.out.println("Not found " + csvFile);
      invalidInput = true;
    }

    for (Map.Entry<String, String> entry : checkMailType.entrySet()) {
      String key = entry.getKey();
      if (input.contains(key) && !input.contains(checkMailType.get(key))) {
        System.out.println("Error: " + key + " provided but no " + checkMailType.get(key) + " was given.");
        invalidInput = true;
      }
    }
    return invalidInput;

  }

  /**
   * Method to get workable inputs in string list
   * @param userInput String we get from user as command line input
   * @return String array having final arguments to be used by generator class
   */
  public String[] getCommandlineInput(String userInput) {
    //length of workable arguments
    arguments = userInput.split(" ");
    String email = "--email";
    String letter = "--letter";
    String emailTemplate = "--email-template";
    String letterTemplate = "--letter-template";
    String outDir = "--output-dir";
    workableArguments = new String[len];
    String csvFile = "--csv-file";

    // gathering final arguments to be used to generate mails
    for (int i = 0; i < arguments.length; i++) {
      if (arguments[i].equals(email) || arguments[i].equals(letter)) {

        workableArguments[zero] = arguments[i];
      }
      if (arguments[i].equals(emailTemplate) || arguments[i].equals(letterTemplate)) {
        workableArguments[one] = arguments[i + 1];
      }
      if (arguments[i].equals(csvFile)) {
        workableArguments[two] = arguments[i + 1];
      }
      if (arguments[i].equals(outDir)) {
        workableArguments[three] = arguments[i + 1];
      }

    }
    return workableArguments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandLineArgumentParser that = (CommandLineArgumentParser) o;
    return Arrays.equals(arguments, that.arguments) && Arrays.equals(
        workableArguments, that.workableArguments);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(arguments);
    result = 31 * result + Arrays.hashCode(workableArguments);
    return result;
  }

  @Override
  public String toString() {
    return "CommandLineArgumentParser{" +
        "arguments=" + Arrays.toString(arguments) +
        ", workableArguments=" + Arrays.toString(workableArguments) +
        '}';
  }
}
