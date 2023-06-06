
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PaperTowelsTest {

  PaperTowels paperTowels;

  @BeforeEach
  void setUp() {
    paperTowels = new PaperTowels();
    paperTowels.setManufacturer("sameer");
    paperTowels.setProductName("head");
    paperTowels.setPrice(50);
    paperTowels.setMinimumAge(18);
    paperTowels.setNumOfUnits(10);

  }

  @Test
  void testManufacturer() {
    paperTowels.setManufacturer("sameer");
    assertEquals("sameer", paperTowels.getManufacturer());
  }

  @Test
  void testProductName() {
    paperTowels.setProductName("head");
    assertEquals("head", paperTowels.getProductName());
  }

  @Test
  void testPrice() {
    paperTowels.setPrice(50);
    assertEquals(50, paperTowels.getPrice());
  }

  @Test
  void testMinimumAge() {
    paperTowels.setMinimumAge(18);
    assertEquals(18, paperTowels.getMinimumAge());
  }

  @Test
  void testNumOfUnits() {
    paperTowels.setNumOfUnits(10);
    assertEquals(10, paperTowels.getNumOfUnits());
  }

  @Test
  void testHashCode() {

    assertEquals(paperTowels.hashCode(), paperTowels.hashCode());
  }

  @Test
  void testEquals() {
    assertTrue(paperTowels.equals(paperTowels));
    assertFalse(paperTowels.equals(null));
    PaperTowels paperTowels1 = new PaperTowels();
    paperTowels1.setProductName("head");
    paperTowels1.setManufacturer("sameer");
    paperTowels1.setPrice(50);
    paperTowels1.setMinimumAge(18);
    paperTowels1.setNumOfUnits(10);
    assertTrue(paperTowels.equals(paperTowels1));

  }
}