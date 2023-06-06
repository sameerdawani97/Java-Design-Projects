import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CsvReaderTest {
  CsvReader csvReader;
  @BeforeEach
  void setUp() {
    csvReader = new CsvReader();
  }

  @Test
  void testCsvReader() throws FileNotFoundException, Exception {
    Set<Map<String,String>> customerDataset = new HashSet<>();
    Set<Map<String,String>> customerData = new HashSet<>();
    customerData = csvReader.getCustomerCollection("insurance-company-members.csv");
    assertNotEquals(customerDataset,customerData);
  }
  @Test
  void testHashCodeAndEquals() throws FileNotFoundException, Exception{
    CsvReader csvReader1 = new CsvReader();
    Set<Map<String,String>> customerData = new HashSet<>();
    customerData = csvReader.getCustomerCollection("insurance-company-members.csv");
    assertTrue(csvReader.hashCode()==csvReader.hashCode());
    assertFalse(csvReader.equals(null));
    assertTrue(csvReader.equals(csvReader));
    assertFalse(csvReader.equals(csvReader1));

  }
  @Test
  void testToString(){
    assertEquals(csvReader.toString(),csvReader.toString());
  }
}
