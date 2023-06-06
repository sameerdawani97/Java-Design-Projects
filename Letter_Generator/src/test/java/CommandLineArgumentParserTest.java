import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLineArgumentParserTest {

  CommandLineArgumentParser commandLineArgumentParser;
  @BeforeEach
  void setUp() {
    commandLineArgumentParser = new CommandLineArgumentParser();
  }

  @Test
  void testCheckValidInput() throws Exception{
    String input = "";
    assertEquals(true,commandLineArgumentParser.checkValidCommandLineInput(input));
    commandLineArgumentParser.getCommandlineInput(input);
    input = "--email --letter";
    assertEquals(true,commandLineArgumentParser.checkValidCommandLineInput(input));
    commandLineArgumentParser.getCommandlineInput(input);

  }
  @Test
  void testEqualsAndHashCode() throws Exception{
    CommandLineArgumentParser commandLineArgumentParser1 = new CommandLineArgumentParser();
    String input = "--email --letter";
    commandLineArgumentParser.checkValidCommandLineInput(input);


    assertEquals(commandLineArgumentParser.hashCode(),commandLineArgumentParser.hashCode());
    assertTrue(commandLineArgumentParser.equals(commandLineArgumentParser));
    assertFalse(commandLineArgumentParser.equals(null));


  }
  @Test
  void testToString(){
    assertEquals(commandLineArgumentParser.toString(),commandLineArgumentParser.toString());
  }
}
