import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;


class CommandLineInterfaceTest {

  private static CommandLineInterface commandLineInterfaceManager;
  private  static ByteArrayOutputStream outputContent;
  private static String data;

  private static final PrintStream originalOutput = System.out;
  private static final InputStream originalInput = System.in;

  @BeforeAll
  static void beforeAll() throws IOException, ParseException {
    commandLineInterfaceManager= new CommandLineInterface();
    data="1\nn\n-1\n2\no\no\nn\nq";
    System.setIn(new ByteArrayInputStream(data.getBytes()));
    //Collecting output sent to command prompt into an outputStream for testing
    outputContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputContent));

  }


  @Test
  void testStart() throws IOException, ParseException {
    commandLineInterfaceManager.start();
    System.setOut(originalOutput);
    String outputString="Loading grammars...\n"
        + "The Following grammars are available:\n"
        + "1. Insult Generator\n"
        + "2. Poem Generator\n"
        + "3. Term Paper Generator\n"
        + "What would you like to use? (q to quit)\n"
        + "You are so pompous that even a pop tart would not want to caress you.\n"
        + "Would you like another? (y/n)\n"
        + "The Following grammars are available:\n"
        + "1. Insult Generator\n"
        + "2. Poem Generator\n"
        + "3. Term Paper Generator\n"
        + "What would you like to use? (q to quit)\n"
        + "Invalid input, choose from options between 1 - 3\n"
        + "The Following grammars are available:\n"
        + "1. Insult Generator\n"
        + "2. Poem Generator\n"
        + "3. Term Paper Generator\n"
        + "What would you like to use? (q to quit)\n"
        + "The big yellow flowers sigh grumpily tonight.\n"
        + "Would you like another? (y/n)\n"
        + "Invalid input, please input y/n\n"
        + "Invalid input, please input y/n\n"
        + "The Following grammars are available:\n"
        + "1. Insult Generator\n"
        + "2. Poem Generator\n"
        + "3. Term Paper Generator\n"
        + "What would you like to use? (q to quit)\n"
        + "Exit\n";
    assertEquals(outputString,outputContent.toString().replaceAll("\r",""));
  }
  @Test
  void testEquals() {
    assertEquals(commandLineInterfaceManager,commandLineInterfaceManager);
    assertFalse(commandLineInterfaceManager.equals(null));
  }
  @AfterAll
  static void afterAll() {
    System.setOut(originalOutput);
    System.setIn(originalInput);
  }
}
