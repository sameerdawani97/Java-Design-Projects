import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Objects;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonParserTest {

  JsonParser testJsonParser, testJsonParser1;

  @BeforeEach
  void setUp() throws IOException, ParseException {
    testJsonParser = new JsonParser("grammars");
    testJsonParser1 = new JsonParser("grammars");
  }



  @Test
  void getGrammarTitles() throws IOException, ParseException {
    System.out.println(testJsonParser.getGrammarTitles());
  }

  @Test
  void getGrammarByUserInput() throws IOException, ParseException {
   // System.out.println(JsonParser.getGrammarByUserInput(0));
  }

  @Test
  void testEquals() {
    assertFalse(testJsonParser.equals(null));
    assertTrue(testJsonParser.equals(testJsonParser));
    assertTrue(testJsonParser.equals(testJsonParser1));
    assertFalse(testJsonParser.equals(new SentenceGeneratorTest()));
  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hashCode(testJsonParser), testJsonParser.hashCode());
  }


}