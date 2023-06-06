import Exception.StockOutOfStockException;
import java.util.Objects;

/**
 * Class to create stock item object for product and its quantity in stock
 */
public class StockItem {
  private Product product;
  private int quantity;

  /**
   * Constructor for stock item
   * @param product product
   * @param quantity quantity
   */
  public StockItem(Product product, int quantity){
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * Method to validate if there are enough items to purchase
   * @param quantityRequired quantityRequired
   * @return validation
   */
  public boolean isEnoughItems(int quantityRequired){
    return quantityRequired<=this.quantity;
  }

  /**
   * Method to update item quantity in case of purchase
   * @param quantityPurchased quantityPurchased
   * @throws StockOutOfStockException If item is out of stock
   */
  public void updateItemQuantity(int quantityPurchased) throws StockOutOfStockException{
    if(isEnoughItems(quantityPurchased)){
      this.quantity-=quantityPurchased;
    }
    else{
      throw new StockOutOfStockException("Product out of stock");
    }

  }

  /**
   * Method to get product
   * @return product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Method to set product
   * @param product product
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Method to get quantity
   * @return quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Method to set quantity
   * @param quantity quantity
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StockItem stockItem = (StockItem) o;
    return quantity == stockItem.quantity && Objects.equals(product, stockItem.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }
}
