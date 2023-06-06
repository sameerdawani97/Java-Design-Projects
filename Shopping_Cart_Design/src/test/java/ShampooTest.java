
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ShampooTest {

  Shampoo shampoo;
  @BeforeEach
  void setUp() {
    shampoo = new Shampoo();
    shampoo.setManufacturer("sameer");
    shampoo.setProductName("head");
    shampoo.setPrice(50);
    shampoo.setMinimumAge(18);
    shampoo.setNumOfUnits(10);

  }
  @Test
  void testManufacturer(){
    shampoo.setManufacturer("sameer");
    assertEquals("sameer",shampoo.getManufacturer());
  }
  @Test
  void testProductName(){
    shampoo.setProductName("head");
    assertEquals("head",shampoo.getProductName());
  }

  @Test
  void testPrice(){
    shampoo.setPrice(50);
    assertEquals(50,shampoo.getPrice());
  }

  @Test
  void testMinimumAge(){
    shampoo.setMinimumAge(18);
    assertEquals(18,shampoo.getMinimumAge());
  }

  @Test
  void testNumOfUnits(){
    shampoo.setNumOfUnits(10);
    assertEquals(10,shampoo.getNumOfUnits());
  }

  @Test
  void testHashCode(){

    assertEquals(shampoo.hashCode(),shampoo.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(shampoo.equals(shampoo));
    assertFalse(shampoo.equals(null));
    Shampoo shampoo1 = new Shampoo();
    shampoo1.setProductName("head");
    shampoo1.setManufacturer("sameer");
    shampoo1.setPrice(50);
    shampoo1.setMinimumAge(18);
    shampoo1.setNumOfUnits(10);
    assertTrue(shampoo.equals(shampoo1));

  }

}

