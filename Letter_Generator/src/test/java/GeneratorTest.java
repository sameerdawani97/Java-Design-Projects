import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

  Generator generator;
  @BeforeEach
  void setUp() {
    generator = new Generator();
  }

  @Test
  void testGenerateFiles() throws Exception{
    generator.generateFiles("custom-letterTemplate.txt","letter","insurance-company-members.csv");
  }

  @Test
  void testHashCodeAndEquals() throws Exception{
    Generator generator1 = new Generator();
    generator.generateFiles("custom-letterTemplate.txt","letter","insurance-company-members.csv");
    assertTrue(generator.equals(generator));
    assertFalse(generator.equals(null));
    assertFalse(generator.equals(generator1));
    assertEquals(generator.hashCode(),generator.hashCode());
  }
  @Test
  void testToString(){
    assertEquals(generator.toString(),generator.toString());
  }

}
