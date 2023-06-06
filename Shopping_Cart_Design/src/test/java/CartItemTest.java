import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CartItemTest {

  CartItem cartItem;
  Beer beer;
  @BeforeEach
  void setUp() {
    beer = new Beer();
    beer.setProductName("Beer");
    beer.setPrice(50);
    beer.setWeight(30);
    cartItem = new CartItem(beer,2);
  }

  @Test
  void testProduct(){
    assertEquals(beer,cartItem.getProduct());
  }

  @Test
  void testQuantity(){
    assertEquals(2,cartItem.getQuantity());
  }

  @Test
  void testHashCode(){
    assertEquals(cartItem.hashCode(),cartItem.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(beer.equals(beer));
    assertFalse(beer.equals(null));
    Beer beer1 = new Beer();
    beer1.setProductName("Beer");
    beer1.setPrice(50);
    beer1.setWeight(30);
    CartItem cartItem1 = new CartItem(beer1,2);
    assertTrue(cartItem.equals(cartItem1));
  }
}
