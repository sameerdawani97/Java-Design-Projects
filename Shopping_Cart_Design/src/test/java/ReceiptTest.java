import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ReceiptTest {

  Receipt receipt;
  @BeforeEach
  void setUp() {
    receipt = new Receipt();
  }

  @Test
  void testProductList(){
    List<CartItem> current = new ArrayList<CartItem>();
    receipt.setProductList(current);
    assertEquals(current,receipt.getProductList());
  }

  @Test
  void testOutOfStockList(){
    List<CartItem> current = new ArrayList<CartItem>();
    receipt.setProductListOutOfStock(current);
    assertEquals(current,receipt.getProductListOutOfStock());
  }

  @Test
  void testRemovedProductList(){
    List<CartItem> current = new ArrayList<CartItem>();
    receipt.setProductListRemoved(current);
    assertEquals(current,receipt.getProductListRemoved());
  }

  @Test
  void testAddToProductsReceived(){
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    receipt.addToProductsReceivedByCustomer(cartItem);
    assertEquals(250,receipt.getTotalPricePaid());
  }

  @Test
  void testAddToOutOfStock(){
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    List<CartItem> current = new ArrayList<CartItem>();
    current.add(cartItem);
    receipt.addToProductsOutOfStock(cartItem);
    assertEquals(current,receipt.getProductListOutOfStock());
  }

  @Test
  void testAddToRemovedList(){
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    List<CartItem> removedList = new ArrayList<CartItem>();
    removedList.add(cartItem);
    receipt.addToProductsRemoved(cartItem);
    assertEquals(removedList,receipt.getProductListRemoved());
  }

  @Test
  void testHashcode(){
    assertEquals(receipt.hashCode(),receipt.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(receipt.equals(receipt));
    assertFalse(receipt.equals(null));
    assertFalse(receipt.equals(new Beer()));
    Receipt receipt1 = new Receipt();
    assertTrue(receipt.equals(receipt1));


  }
}
