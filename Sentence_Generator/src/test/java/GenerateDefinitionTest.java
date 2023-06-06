import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenerateDefinitionTest {

  private Grammar testGrammar;
  private HashMap<String, Object> testHashMap;
  GenerateDefinition testGenerateDefinition;

  @BeforeEach
  void setUp() {
    testHashMap = new LinkedHashMap();
    testHashMap.put("start", Arrays.asList("The <object> <verb> tonight."));
    testHashMap.put("object", Arrays.asList("waves","big yellow flowers","slugs"));
    testHashMap.put("verb", Arrays.asList("sigh <adverb>","portend like <object>","die <adverb>"));
    testHashMap.put("adverb", Arrays.asList("warily","grumpily"));
    testGrammar =new Grammar(testHashMap);
    testGenerateDefinition = new GenerateDefinition(testGrammar);
  }

  @Test
  void getDefinition() {
    assertEquals(new TerminalString("sun"), testGenerateDefinition.getDefinition("sun"));
    assertEquals(new TerminalString("a"), testGenerateDefinition.getDefinition("a"));
    assertEquals(new SpecialCharacter(","), testGenerateDefinition.getDefinition(","));
    assertNotEquals(new NonTerminalString("<adj> sun rises"), testGenerateDefinition.getDefinition("<adj> sun rises"));
  }

  @Test
  void testEquals() {
    assertNotEquals(testGenerateDefinition, null);
    assertEquals(testGenerateDefinition, testGenerateDefinition);
    assertEquals(new GenerateDefinition(testGrammar), testGenerateDefinition);
    assertNotEquals("?", testGenerateDefinition);
  }

  @Test
  void testHashCode() {
    int hashCode = Objects.hash(testGrammar);
    assertEquals(hashCode, testGenerateDefinition.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("GenerateDefinition{" +
        "grammar=" + testGrammar +
        '}', testGenerateDefinition.toString());
  }
}