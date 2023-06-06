import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockItemTest {

  StockItem stockItem;
  PaperTowels paperTowels;
  @BeforeEach
  void setUp() {
    paperTowels = new PaperTowels();
    paperTowels.setProductName("PaperTowel");
    paperTowels.setPrice(50);
    paperTowels.setNumOfUnits(10);
    stockItem = new StockItem(paperTowels,10);
  }

  @Test
  void testProduct(){
    stockItem.setProduct(paperTowels);
    assertEquals(paperTowels,stockItem.getProduct());
  }

  @Test
  void testQuantity(){
    assertEquals(10,stockItem.getQuantity());
  }

  @Test
  void testHashCode(){
    assertEquals(stockItem.hashCode(),stockItem.hashCode());
  }

  @Test
  void testUpdateItemQuantity(){
    stockItem.updateItemQuantity(4);
    assertEquals(6,stockItem.getQuantity());
  }
  @Test
  void testEquals(){
    assertTrue(paperTowels.equals(paperTowels));
    assertFalse(paperTowels.equals(null));
    PaperTowels paperTowels1 = new PaperTowels();
    paperTowels1.setProductName("PaperTowel");
    paperTowels1.setPrice(50);
    paperTowels1.setNumOfUnits(10);
    StockItem stockItem1 = new StockItem(paperTowels1,10);
    assertTrue(stockItem.equals(stockItem1));
  }
}
