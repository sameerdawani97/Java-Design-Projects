import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SentenceGeneratorTest {

  private HashMap<String, Object> testRules;
  private Grammar testGrammar;
  private SentenceGenerator testSentenceGenerator;
  private GenerateDefinition testGenerateDefinition;

  @BeforeEach
  void setUp() {
    testRules = new LinkedHashMap();
    testRules.put("start", Arrays.asList("The <object> <verb> tonight."));
    testRules.put("object", Arrays.asList("waves","big yellow flowers","slugs"));
    testRules.put("verb", Arrays.asList("sigh <adverb>","portend like <object>","die <adverb>"));
    testRules.put("adverb", Arrays.asList("warily","grumpily"));
    testGrammar =new Grammar(testRules);
    testSentenceGenerator = new SentenceGenerator(testGrammar);
    testGenerateDefinition = new GenerateDefinition(testGrammar);
  }

  @Test
  void generateSentence() {
    System.out.println(testSentenceGenerator.generateSentence());
    assertEquals("The waves portend like big yellow flowers tonight.", testSentenceGenerator.generateSentence());
    testRules.put("start",Arrays.asList("The <object> <verbs> tonight."));

    Throwable exception1 = assertThrows(NonTerminalNotDefinedException.class,
        () -> testSentenceGenerator.generateSentence());
    assertEquals("The Non-Terminal is not defined in the grammar : verbs", exception1.getMessage());
  }

  @Test
  void testEquals() {
    assertFalse(testSentenceGenerator.equals(null));
    assertEquals(testSentenceGenerator, testSentenceGenerator);
    assertEquals(new SentenceGenerator(testGrammar), testSentenceGenerator);
  }

  @Test
  void testHashCode() {
    int expectedHashCode = Objects.hash(testGrammar, testGenerateDefinition);
    assertEquals(expectedHashCode, testSentenceGenerator.hashCode());
  }

  @Test
  void testToString() {
    assertEquals("SentenceGenerator{" +
        "grammar=" + testGrammar +
        ", generateDefinition=" + testGenerateDefinition +
        '}', testSentenceGenerator.toString());
  }
}