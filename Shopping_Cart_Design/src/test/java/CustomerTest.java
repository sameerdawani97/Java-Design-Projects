import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CustomerTest {

  Customer customer;
  Inventory inventory;
  @BeforeEach
  void setUp() {
    customer = new Customer();
    inventory = new Inventory();

  }

  @Test
  void testInventory(){
    customer.setInventory(inventory);
    assertEquals(inventory,customer.getInventory());
  }

  @Test
  void testShoppingCart(){
    ShoppingCart shoppingCart = new ShoppingCart();
    customer.setShoppingCart(shoppingCart);
    assertEquals(shoppingCart,customer.getShoppingCart());
  }

  @Test
  void testName(){
    Name name = new Name("sam","daw");
    customer.setName(name);
    assertEquals(name,customer.getName());
  }

  @Test
  void testAge(){
    customer.setAge(20);
    assertEquals(20,customer.getAge());
  }

  @Test
  void testAddProductToCart(){

    Beer beer = new Beer();
    beer.setProductName("beer");
    beer.setWeight(10);
    beer.setPrice(10);
    StockItem stockItem = new StockItem(beer,10);

    Shampoo shampoo = new Shampoo();
    shampoo.setProductName("shampoo");
    shampoo.setNumOfUnits(10);
    shampoo.setPrice(10);
    StockItem stockItem1 = new StockItem(shampoo,10);

    inventory.addProductToInventory(stockItem);
    inventory.addProductToInventory(stockItem1);
    customer.setInventory(inventory);

    ShoppingCart shoppingCart = new ShoppingCart();
    customer.setShoppingCart(shoppingCart);
    customer.addProductToCart(beer);
    customer.addProductToCart(shampoo);
    assertEquals(2,customer.getShoppingCart().getCart().size());

    customer.addProductLargeQuantity(beer,5);
    customer.addProductLargeQuantity(shampoo,5);
    assertEquals(2,customer.getShoppingCart().getCart().size());
  }

  @Test
  void testOrderProcessing(){
    Beer beer = new Beer();
    beer.setProductName("beer");
    beer.setWeight(10);
    beer.setPrice(10);
    beer.setMinimumAge(18);
    StockItem stockItem = new StockItem(beer,10);

    Shampoo shampoo = new Shampoo();
    shampoo.setProductName("shampoo");
    shampoo.setNumOfUnits(10);
    shampoo.setPrice(10);
    StockItem stockItem1 = new StockItem(shampoo,10);

    inventory.addProductToInventory(stockItem);
    inventory.addProductToInventory(stockItem1);
    customer.setInventory(inventory);

    ShoppingCart shoppingCart = new ShoppingCart();
    Receipt receipt = new Receipt();
    shoppingCart.setReceipt(receipt);
    customer.setAge(0);
    customer.setShoppingCart(shoppingCart);
    customer.addProductToCart(beer);
    customer.addProductToCart(shampoo);
    customer.OrderProcessing();
    assertEquals(1,shoppingCart.getReceipt().getProductList().size());
    assertEquals(1,shoppingCart.getReceipt().getProductListRemoved().size());

  }

  @Test
  void testOrderProcessing2(){
    Beer beer = new Beer();
    beer.setProductName("beer");
    beer.setWeight(10);
    beer.setPrice(10);
    beer.setMinimumAge(18);
    StockItem stockItem = new StockItem(beer,2);

    Shampoo shampoo = new Shampoo();
    shampoo.setProductName("shampoo");
    shampoo.setNumOfUnits(10);
    shampoo.setPrice(10);
    StockItem stockItem1 = new StockItem(shampoo,10);

    inventory.addProductToInventory(stockItem);
    inventory.addProductToInventory(stockItem1);
    customer.setInventory(inventory);

    ShoppingCart shoppingCart = new ShoppingCart();
    List<CartItem> current = new ArrayList<CartItem>();
    current.add(new CartItem(beer,5));
    shoppingCart.setCart(current);
    Receipt receipt = new Receipt();
    shoppingCart.setReceipt(receipt);
    customer.setAge(0);
    customer.setShoppingCart(shoppingCart);
    customer.addProductToCart(shampoo);
    customer.OrderProcessing();
    assertEquals(1,customer.getShoppingCart().getReceipt().getProductListOutOfStock().size());
  }

  @Test
  void testHashCode(){
    assertEquals(customer.hashCode(),customer.hashCode());
  }

  @Test
  void testEquals(){
    Beer beer = new Beer();
    Name name = new Name("sa","da");
    ShoppingCart shoppingCart = new ShoppingCart();
    customer.setAge(2);
    customer.setName(name);
    customer.setInventory(inventory);
    customer.setShoppingCart(shoppingCart);

    Customer customer1 =new Customer();
    customer1.setAge(2);
    customer1.setName(name);
    customer1.setInventory(inventory);
    customer1.setShoppingCart(shoppingCart);

    assertTrue(customer.equals(customer));
    assertFalse(customer.equals(null));
    assertFalse(customer.equals(beer));
    assertTrue(customer.equals(customer1));

  }

}
