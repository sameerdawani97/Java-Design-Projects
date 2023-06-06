import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrammarTest {

  private Grammar testGrammar;
  private HashMap<String, Object> testRules;

  @BeforeEach
  void setUp() {
    testRules = new LinkedHashMap();
    testRules.put("grammarTitle","Poem Generator");
    testRules.put("start", Arrays.asList("The <object> <verb> tonight."));
    testRules.put("object", Arrays.asList("waves","big yellow flowers","slugs"));
    testRules.put("verb", Arrays.asList("sigh <adverb>","portend like <object>","die <adverb>"));
    testRules.put("adverb", Arrays.asList("warily","grumpily"));
    testGrammar = new Grammar(testRules);
  }

  @Test
  void getGrammarTitle() {
    assertEquals("Poem Generator", testGrammar.getGrammarTitle());
  }

  @Test
  void getGrammarStart() {
    assertEquals(Arrays.asList("The <object> <verb> tonight."), testGrammar.getGrammarStart());
  }

  @Test
  void checkRule() {
    assertFalse(testGrammar.checkRule("abc"));
    assertTrue(testGrammar.checkRule("adverb"));
  }

  @Test
  void getRandomProduction() {
    assertEquals("grumpily", testGrammar.getRandomProduction("adverb"));
  }

  @Test
  void testEquals() {
    assertFalse(testGrammar.equals(null));
    assertEquals(testGrammar, testGrammar);
    HashMap<String,Object> hashMap1 = new LinkedHashMap();
    hashMap1.put("grammarTitle","Poem Generator");
    hashMap1.put("start", Arrays.asList("The <object> <verb> tonight."));
    hashMap1.put("object", Arrays.asList("waves","big yellow flowers","slugs"));
    hashMap1.put("verb", Arrays.asList("sigh <adverb>","portend like <object>","die <adverb>"));
    hashMap1.put("adverb", Arrays.asList("warily","grumpily"));
    assertEquals(new Grammar(hashMap1), testGrammar);
  }

  @Test
  void testHashCode() {
    int hashCode = Objects.hash(testRules);
    assertEquals(hashCode, testGrammar.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Grammar{" + "rules=" + testRules + '}';
    assertEquals(expectedString, testGrammar.toString());
  }
}