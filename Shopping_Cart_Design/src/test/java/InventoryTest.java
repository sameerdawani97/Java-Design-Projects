import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class InventoryTest {

  Inventory inventory;
  StockItem stockItem;
  StockItem stockItem1;
  @BeforeEach
  void setUp() {
    Beer beer = new Beer();
    beer.setProductName("beer");
    beer.setWeight(10);
    beer.setPrice(10);

    stockItem = new StockItem(beer,10);

    Shampoo shampoo = new Shampoo();
    shampoo.setProductName("shampoo");
    shampoo.setNumOfUnits(10);
    shampoo.setPrice(10);

    stockItem1 = new StockItem(shampoo,10);

    inventory = new Inventory();
  }

  @Test
  void testGroceryInventory(){
    List<StockItem> groceryInventory = new ArrayList<StockItem>();
    inventory.setGroceryInventory(groceryInventory);
    assertEquals(groceryInventory,inventory.getGroceryInventory());
  }

  @Test
  void testHouseHoldInventory(){
    List<StockItem> houseHoldInventory = new ArrayList<StockItem>();
    inventory.setHouseholdInventory(houseHoldInventory);
    assertEquals(houseHoldInventory,inventory.getHouseholdInventory());
  }

  @Test
  void testAddProductToInventory(){
    List<StockItem> groceryInventory = new ArrayList<StockItem>();
    groceryInventory.add(stockItem);
    inventory.addProductToInventory(stockItem);
    assertEquals(inventory.getGroceryInventory(),groceryInventory);

    inventory.addProductToInventory(stockItem);
    groceryInventory.get(0).setQuantity(stockItem.getQuantity()*2);
    assertEquals(inventory.getGroceryInventory(),groceryInventory);

    List<StockItem> houseHoldInventory = new ArrayList<StockItem>();
    houseHoldInventory.add(stockItem1);
    inventory.addProductToInventory(stockItem1);
    assertEquals(inventory.getGroceryInventory(),groceryInventory);

    houseHoldInventory.get(0).setQuantity(stockItem1.getQuantity()*2);
    inventory.addProductToInventory(stockItem1);
    assertEquals(inventory.getHouseholdInventory(),houseHoldInventory);

  }

  /**
   * because this is single ton inventory class so each time inventory is updated.
   * below test cases will run if we run complete class because I set the value accordingly
   */
  @Test
  void testUpdateInventory(){
    CartItem cartItem = new CartItem(stockItem.getProduct(),stockItem.getQuantity());
    cartItem.setQuantity(5);
    inventory.addProductToInventory(stockItem);
    inventory.updateInventory(cartItem);
    assertEquals(5,inventory.getGroceryInventory().get(0).getQuantity());

    CartItem cartItem1 = new CartItem(stockItem1.getProduct(),stockItem1.getQuantity());
    cartItem1.setQuantity(5);
    inventory.addProductToInventory(stockItem1);
    inventory.updateInventory(cartItem1);
    assertEquals(5,inventory.getHouseholdInventory().get(0).getQuantity());


  }

  @Test
  void testGetTotalRetailValue(){
    inventory.addProductToInventory(stockItem);
    inventory.addProductToInventory(stockItem1);
    assertEquals(200,inventory.getTotalRetailItemValue());
  }

  @Test
  void testSubstituteProduct(){
    inventory.addProductToInventory(stockItem);
    inventory.addProductToInventory(stockItem1);


    Beer beer1 = new Beer();
    beer1.setProductName("beer");
    beer1.setWeight(10);
    beer1.setPrice(12);

    StockItem stockItem2 = new StockItem(beer1,5);

    Shampoo shampoo1 = new Shampoo();
    shampoo1.setProductName("shampoo");
    shampoo1.setNumOfUnits(10);
    shampoo1.setPrice(12);
    StockItem stockItem3 = new StockItem(shampoo1,5);

    inventory.addProductToInventory(stockItem2);
    inventory.addProductToInventory(stockItem3);


    CartItem cartItem = new CartItem(stockItem.getProduct(),stockItem.getQuantity());
    CartItem cartItem1 = new CartItem(stockItem1.getProduct(),stockItem1.getQuantity());
    CartItem cartItem2 = new CartItem(stockItem2.getProduct(),stockItem2.getQuantity());
    CartItem cartItem3 = new CartItem(stockItem3.getProduct(),stockItem3.getQuantity());


    CartItem item = inventory.getSubstituteProduct(cartItem2);
    CartItem item1 = inventory.getSubstituteProduct(cartItem3);

    CartItem item2 = inventory.getSubstituteProduct(cartItem);
    CartItem item3 = inventory.getSubstituteProduct(cartItem1);


    assertEquals(cartItem.getProduct(),item.getProduct());
    assertEquals(cartItem1.getProduct(),item1.getProduct());

    assertNotEquals(cartItem2.getProduct(),null);
    assertNotEquals(cartItem3.getProduct(),null);

  }

  @Test
  void testHashcode(){
    assertEquals(inventory.hashCode(),inventory.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(inventory.equals(inventory));
    Inventory inventory1 = new Inventory();
    assertFalse(inventory.equals(null));
    Beer beer = new Beer();
    assertFalse(inventory.equals(beer));
    assertTrue(inventory.equals(inventory1));
  }


}
