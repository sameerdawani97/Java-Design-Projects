import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ShoppingCartTest {

  ShoppingCart shoppingCart;
  @BeforeEach
  void setUp() {
    shoppingCart = new ShoppingCart();
  }

  @Test
  void testCart(){
    List<CartItem> current = new ArrayList<CartItem>();
    shoppingCart.setCart(current);
    assertEquals(current,shoppingCart.getCart());
  }

  @Test
  void testTotalCost(){
    shoppingCart.setTotalCostOfCart(5.0);
    assertEquals(5.0,shoppingCart.getTotalCostOfCart());
  }

  @Test
  void testReceipt(){
    Receipt receipt = new Receipt();
    shoppingCart.setReceipt(receipt);
    assertEquals(receipt,shoppingCart.getReceipt());

  }

  @Test
  void testAddProductCart(){
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    shoppingCart.addProductToCart(cartItem);
    assertEquals(250,shoppingCart.getTotalCostOfCart());


    CartItem cartItem1 = new CartItem(beer,5);
    shoppingCart.addProductToCart(cartItem1);
    assertEquals(500,shoppingCart.getTotalCostOfCart());
  }

  @Test
  void testAddToCustomerReceived(){
    Receipt receipt = new Receipt();
    shoppingCart.setReceipt(receipt);
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    shoppingCart.addProductListCustomerReceived(cartItem);
    assertEquals(cartItem,shoppingCart.getReceipt().getProductList().get(0));

  }

  @Test
  void testAddToRemovedList(){
    Receipt receipt = new Receipt();
    shoppingCart.setReceipt(receipt);
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    shoppingCart.addProductListRemoved(cartItem);
    assertEquals(cartItem,shoppingCart.getReceipt().getProductListRemoved().get(0));

  }
  @Test
  void testAddToOutOfStockList(){
    Receipt receipt = new Receipt();
    shoppingCart.setReceipt(receipt);
    Beer beer = new Beer();
    beer.setPrice(50);
    CartItem cartItem = new CartItem(beer,5);
    shoppingCart.addProductListOutOfStock(cartItem);
    assertEquals(cartItem,shoppingCart.getReceipt().getProductListOutOfStock().get(0));

  }

  @Test
  void testHashCode(){
    assertEquals(shoppingCart.hashCode(),shoppingCart.hashCode());

  }

  @Test
  void testEquals(){
    assertTrue(shoppingCart.equals(shoppingCart));
    assertFalse(shoppingCart.equals(null));
    Beer beer = new Beer();
    assertFalse(shoppingCart.equals(beer));
    ShoppingCart shoppingCart1 = new ShoppingCart();
    assertTrue(shoppingCart1.equals(shoppingCart));
  }


}
