
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SalmonTest {

  Salmon salmon;
  @BeforeEach
  void setUp() {
    salmon = new Salmon();
    salmon.setWeight(10);
  }
  @Test
  void testManufacturer(){
    salmon.setManufacturer("sameer");
    assertEquals("sameer",salmon.getManufacturer());
  }
  @Test
  void testProductName(){
    salmon.setProductName("cheese");
    assertEquals("cheese",salmon.getProductName());
  }

  @Test
  void testPrice(){
    salmon.setPrice(50);
    assertEquals(50,salmon.getPrice());
  }

  @Test
  void testMinimumAge(){
    salmon.setMinimumAge(18);
    assertEquals(18,salmon.getMinimumAge());
  }

  @Test
  void testWeight(){
    salmon.setWeight(10);
    assertEquals(10,salmon.getWeight());
  }
  @Test
  void testHashCode(){

    assertEquals(salmon.hashCode(),salmon.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(salmon.equals(salmon));
    assertFalse(salmon.equals(null));
    Salmon salmon1 = new Salmon();
    salmon1.setProductName("cheese");
    salmon1.setManufacturer("sameer");
    salmon1.setPrice(50);
    salmon1.setMinimumAge(18);
    salmon1.setWeight(10);
    assertFalse(salmon.equals(salmon1));

    Salmon salmon2 = new Salmon();
    salmon2.setProductName("cheese");
    salmon2.setManufacturer("sameer");
    salmon2.setPrice(50);
    salmon2.setMinimumAge(18);
    salmon2.setWeight(10);
    assertTrue(salmon2.equals(salmon1));

  }

}

