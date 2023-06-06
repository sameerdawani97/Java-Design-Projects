import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Inventory class to store inventory of products
 */
public class Inventory {
  private List<StockItem> GroceryInventory;
  private List<StockItem> HouseholdInventory;

  private final static int COMPARISON_VALUE = 0;
  /**
   * Constructor to create object of inventory
   */
  public Inventory() {
    this.GroceryInventory = new ArrayList<StockItem>();
    this.HouseholdInventory = new ArrayList<StockItem>();
  }

  /**
   * Method to get Grocery inventory
   * @return GroceryInventory
   */
  public List<StockItem> getGroceryInventory() {
    return GroceryInventory;
  }

  /**
   * Method to set Grocery inventory
   * @param groceryInventory groceryInventory
   */
  public void setGroceryInventory(List<StockItem> groceryInventory) {
    GroceryInventory = groceryInventory;
  }

  /**
   * Method to get Household inventory
   * @return HouseholdInventory
   */
  public List<StockItem> getHouseholdInventory() {
    return HouseholdInventory;
  }

  /**
   * Method to set Household inventory
   * @param householdInventory householdInventory
   */
  public void setHouseholdInventory(List<StockItem> householdInventory) {
    HouseholdInventory = householdInventory;
  }

  /**
   * Method to add Inventory product
   * @param stockItem stockItem
   */
  public void addProductToInventory(StockItem stockItem){
    if(stockItem.getProduct().getClass().getSuperclass() == Grocery.class){
      addProductInventoryGrocery(stockItem);
    } else if (stockItem.getProduct().getClass().getSuperclass() == HouseHold.class) {
      addProductInventoryHousehold(stockItem);
    }
  }

  /**
   * Method to add grocery inventory product
   * @param stockItem stockItem
   */
  public void addProductInventoryGrocery(StockItem stockItem){
    boolean itemExist = false;
    for (StockItem stock : GroceryInventory){
      if(stock.getProduct().equals(stockItem.getProduct())){
        itemExist = true;
        stock.setQuantity(stock.getQuantity()+stockItem.getQuantity());
      }
    }
    if(!itemExist){
      GroceryInventory.add(stockItem);
    }

  }

  /**
   * Method to add Household inventory product
   * @param stockItem stockItem
   */
  public void addProductInventoryHousehold(StockItem stockItem){
    boolean itemExist = false;
    for (StockItem stock : HouseholdInventory){
      if(stock.getProduct().equals(stockItem.getProduct())){
        itemExist = true;
        stock.setQuantity(stock.getQuantity()+stockItem.getQuantity());
      }
    }
    if(!itemExist){
      HouseholdInventory.add(stockItem);
    }

  }

  /**
   * Method to update inventory when purchase is done
   * @param cartItem cartItem
   */
  public void updateInventory(CartItem cartItem){
    StockItem stockItem = new StockItem(cartItem.getProduct(), cartItem.getQuantity());
    if(stockItem.getProduct().getClass().getSuperclass() == Grocery.class) {
      for(StockItem stock : GroceryInventory){
        if(stock.getProduct().equals(stockItem.getProduct())){
          stock.updateItemQuantity(stockItem.getQuantity());
        }
      }

    } else if (stockItem.getProduct().getClass().getSuperclass() == HouseHold.class) {
      for(StockItem stock : HouseholdInventory){
        if(stock.getProduct().equals(stockItem.getProduct())){
          stock.updateItemQuantity(stockItem.getQuantity());
        }
      }
    }
  }

  /**
   * Method to get total retail item value of total inventory
   * @return totalRetailInventoryValue
   */
  public double getTotalRetailItemValue(){
    double total_value = 0.0;
    // retail value of grocery
    for(StockItem stock : GroceryInventory){
      total_value = total_value + (stock.getProduct().getPrice()*stock.getQuantity());
    }
    //retail value of household
    for(StockItem stock : HouseholdInventory){
      total_value = total_value + (stock.getProduct().getPrice()*stock.getQuantity());
    }
    return total_value;
  }

  /**
   * Method to get substitute product of given product
   * @param cartItem cartItem
   * @return cart Item
   */
  public CartItem getSubstituteProduct(CartItem cartItem){
    if (cartItem.getProduct().getClass().getSuperclass() == Grocery.class) {
      for (StockItem Stock : this.GroceryInventory) {
        Grocery StockProduct = (Grocery) Stock.getProduct();
        Grocery substituteProduct = (Grocery) cartItem.getProduct();
        if (StockProduct.equals(substituteProduct)) {
          continue;
        }
        if (StockProduct.getProductName().equals(substituteProduct.getProductName()) &&
            Double.compare(StockProduct.getPrice(), substituteProduct.getPrice()) <= COMPARISON_VALUE &&
            Double.compare(StockProduct.getWeight(), substituteProduct.getWeight()) >= COMPARISON_VALUE &&
            Stock.getQuantity() >= cartItem.getQuantity()) {
          return new CartItem(StockProduct, cartItem.getQuantity());
        }
      }
    } else if (cartItem.getProduct().getClass().getSuperclass() ==  HouseHold.class) {
      for (StockItem Stock : this.HouseholdInventory) {
        HouseHold StockProduct = (HouseHold) Stock.getProduct();
        HouseHold substituteProduct = (HouseHold) cartItem.getProduct();
        if (StockProduct.equals(substituteProduct)) {
          continue;
        }
        if (StockProduct.getProductName().equals(substituteProduct.getProductName()) &&
            Double.compare(StockProduct.getPrice(), substituteProduct.getPrice()) <= 0 &&
            StockProduct.getNumOfUnits() >= substituteProduct.getNumOfUnits() &&
            Stock.getQuantity() >= cartItem.getQuantity()) {
          return new CartItem(StockProduct, cartItem.getQuantity());
        }
      }
    }
    return null;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Inventory inventory = (Inventory) o;
    return Objects.equals(GroceryInventory, inventory.GroceryInventory)
        && Objects.equals(HouseholdInventory, inventory.HouseholdInventory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(GroceryInventory, HouseholdInventory);
  }

}
