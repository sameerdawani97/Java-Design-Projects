
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ProductTest {

  Cheese cheese;
  @BeforeEach
  void setUp() {
    cheese = new Cheese();
  }
  @Test
  void testManufacturer(){
    cheese.setManufacturer("sameer");
    assertEquals("sameer",cheese.getManufacturer());
  }
  @Test
  void testProductName(){
    cheese.setProductName("cheese");
    assertEquals("cheese",cheese.getProductName());
  }

  @Test
  void testPrice(){
    cheese.setPrice(50);
    assertEquals(50,cheese.getPrice());
  }

  @Test
  void testMinimumAge(){
    cheese.setMinimumAge(18);
    assertEquals(18,cheese.getMinimumAge());
  }
  @Test
  void testHashCode(){

    assertEquals(cheese.hashCode(),cheese.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(cheese.equals(cheese));
    assertFalse(cheese.equals(null));
    Cheese cheese1 = new Cheese();
    cheese1.setProductName("cheese");
    cheese1.setManufacturer("sameer");
    cheese1.setPrice(50);
    cheese1.setMinimumAge(18);
    assertFalse(cheese.equals(cheese1));

    Cheese cheese2 = new Cheese();
    cheese2.setProductName("cheese");
    cheese2.setManufacturer("sameer");
    cheese2.setPrice(50);
    cheese2.setMinimumAge(18);
    assertTrue(cheese2.equals(cheese1));

  }

}
