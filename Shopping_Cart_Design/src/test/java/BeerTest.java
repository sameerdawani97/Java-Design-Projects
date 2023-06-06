
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class BeerTest {

  Beer beer;
  @BeforeEach
  void setUp() {
    beer = new Beer();
    beer.setManufacturer("sameer");
    beer.setProductName("beer");
    beer.setPrice(50);
    beer.setMinimumAge(18);
    beer.setWeight(10);
  }
  @Test
  void testManufacturer(){
    beer.setManufacturer("sameer");
    assertEquals("sameer",beer.getManufacturer());
  }
  @Test
  void testProductName(){
    beer.setProductName("beer");
    assertEquals("beer",beer.getProductName());
  }

  @Test
  void testPrice(){
    beer.setPrice(50);
    assertEquals(50,beer.getPrice());
  }

  @Test
  void testMinimumAge(){
    beer.setMinimumAge(18);
    assertEquals(18,beer.getMinimumAge());
  }
  @Test
  void testHashCode(){

    assertEquals(beer.hashCode(),beer.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(beer.equals(beer));
    assertFalse(beer.equals(null));
    Beer beer1 = new Beer();
    beer1.setProductName("beer");
    beer1.setManufacturer("sameer");
    beer1.setPrice(50);
    beer1.setMinimumAge(18);
    beer1.setWeight(10);
    assertTrue(beer.equals(beer1));

  }

}

