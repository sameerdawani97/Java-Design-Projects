import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemplateProcessorTest {
  TemplateProcessor templateProcessor;
  CsvReader csvReader;
  @BeforeEach
  void setUp() {
    templateProcessor = new TemplateProcessor();
    csvReader = new CsvReader();
  }

  @Test
  void testTemplateParse() throws IOException {
    Set<Map<String,String>> customerData = new HashSet<>();
    customerData = csvReader.getCustomerCollection("insurance-company-members.csv");

    Iterator<Map<String,String>> itr = customerData.iterator();
    Map<String,String> value = null;

    for(int i = 0; itr.hasNext(); i++) {
      value = itr.next();
      if (i == 1) {
        break;
      }
    }
    templateProcessor.parseTemplate("email-template.txt", value);
  }

  @Test
  void testHashCodeAndEquals() throws IOException{
    TemplateProcessor templateProcessor1 = new TemplateProcessor();
    Set<Map<String,String>> customerData = new HashSet<>();
    customerData = csvReader.getCustomerCollection("insurance-company-members.csv");

    Iterator<Map<String,String>> itr = customerData.iterator();
    Map<String,String> value = null;

    for(int i = 0; itr.hasNext(); i++) {
      value = itr.next();
      if (i == 1) {
        break;
      }
    }
    templateProcessor.parseTemplate("email-template.txt", value);
    assertFalse(templateProcessor.hashCode()==templateProcessor1.hashCode());
    assertFalse(templateProcessor.equals(templateProcessor1));
    assertTrue(templateProcessor.equals(templateProcessor));
    assertFalse(templateProcessor.equals(null));

  }

  @Test
  void testToString(){
    assertEquals(templateProcessor.toString(),templateProcessor.toString());
  }
}
