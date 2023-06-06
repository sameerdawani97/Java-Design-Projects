import Exception.StockOutOfStockException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class for customer object
 */
public class Customer {
  private Name name;
  private int age;
  private ShoppingCart shoppingCart;
  private Inventory inventory;

  private final static int MINIMUM_QUANTITY = 1;

  /**
   * Constructor for customer object
   */
  public Customer(){

  }

  /**
   * Method to add product to cart with quantity 1
   * @param product product
   * @throws StockOutOfStockException StockOutOfStockException
   */
  public void addProductToCart(Product product) throws StockOutOfStockException {
    if(checkQuantityInInventory(product,MINIMUM_QUANTITY)){
      shoppingCart.addProductToCart(new CartItem(product, MINIMUM_QUANTITY));
    }
    else{
      throw new StockOutOfStockException("Quantity Larger than Available quantity");
    }
  }

  /**
   * Method to add product to cart with larger quantity
   * @param product product
   * @param quantity quantity
   * @throws StockOutOfStockException StockOutOfStockException
   */
  public void addProductLargeQuantity(Product product, int quantity) throws StockOutOfStockException{
    if(checkQuantityInInventory(product,quantity)){
      shoppingCart.addProductToCart(new CartItem(product, quantity));
    }
    else{
      throw new StockOutOfStockException("Quantity Larger than Available quantity");
    }

  }

  /**
   * Method to check if required quantity is in Inventory
   * @param product product
   * @param quantity quantity
   * @return if stock quantity is sufficient for required quantity
   */
  public boolean checkQuantityInInventory(Product product, int quantity) {
    if (product.getClass().getSuperclass() == Grocery.class) {
      for (StockItem stock : inventory.getGroceryInventory()) {
        if (product.equals(stock.getProduct()) && stock.isEnoughItems(quantity)) {
          return true;
        }

      }
    } else if (product.getClass().getSuperclass() == HouseHold.class) {
      for (StockItem stock : inventory.getHouseholdInventory()) {
        if (product.equals(stock.getProduct()) && stock.isEnoughItems(quantity)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Method to process order and check each condition for successful order processing
   */
  public void OrderProcessing(){
    for (CartItem cartItem : shoppingCart.getCart()){
      if(!checkQuantityInInventory(cartItem.getProduct(), cartItem.getQuantity())){
        CartItem substituteCartItem = inventory.getSubstituteProduct(cartItem);
        if (substituteCartItem==null){
          shoppingCart.addProductListOutOfStock(cartItem);
        }
        else{
          shoppingCart.addProductListCustomerReceived(substituteCartItem);
          inventory.updateInventory(substituteCartItem);
        }
      }
      else{
        if(cartItem.getProduct().getMinimumAge()<=this.age){
          shoppingCart.addProductListCustomerReceived(cartItem);
          inventory.updateInventory(cartItem);
        }
        else{
          shoppingCart.addProductListRemoved(cartItem);
        }

      }

    }
  }

  /**
   * Method to get Inventory object
   * @return inventory
   */
  public Inventory getInventory() {
    return inventory;
  }

  /**
   * Method to set inventory
   * @param inventory inventory
   */
  public void setInventory(Inventory inventory) {
    this.inventory = inventory;
  }

  /**
   * Method to get name
   * @return name
   */
  public Name getName() {
    return name;
  }

  /**
   * Method to set name
   * @param name name
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * Method to get age
   * @return age
   */
  public int getAge() {
    return age;
  }

  /**
   * Method to set age
   * @param age age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Method to get shoppingCart
   * @return shoppingCart
   */
  public ShoppingCart getShoppingCart() {
    return shoppingCart;
  }

  /**
   * Method to set shoppingCart
   * @param shoppingCart shoppingCart
   */
  public void setShoppingCart(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return age == customer.age && Objects.equals(name, customer.name)
        && Objects.equals(shoppingCart, customer.shoppingCart) && Objects.equals(
        inventory, customer.inventory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, shoppingCart, inventory);
  }
}
