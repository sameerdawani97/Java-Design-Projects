import java.util.Objects;

/**
 * Class to create object for cart item
 */
public class CartItem {

  private Product product;
  private int quantity;

  /**
   * Constructor of car item
   * @param product product
   * @param quantity quantity
   */
  public CartItem(Product product, int quantity){
    this.product = product;
    this.quantity = quantity;
  }

  /**
   * Method to get product of cartItem
   * @return product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Method to set product of cartItem
   * @param product product
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Method to get quantity of cartItem
   * @return quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Method to set quantity of cartItem
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
    CartItem cartItem = (CartItem) o;
    return quantity == cartItem.quantity && Objects.equals(product, cartItem.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }
}
