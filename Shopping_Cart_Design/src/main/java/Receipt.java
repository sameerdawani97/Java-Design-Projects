import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for receipt of order
 */
public class Receipt {
  private static Double MINIMUM_PRICE_PAID = 0.0;
  private double totalPricePaid;
  private List<CartItem> productListCustomerReceived;
  private List<CartItem> productListOutOfStock;
  private List<CartItem> productListRemoved;


  /**
   * Constructor for receipt
   */
  public Receipt() {
    this.totalPricePaid = MINIMUM_PRICE_PAID;
    this.productListCustomerReceived = new ArrayList();
    this.productListOutOfStock = new ArrayList();
    this.productListRemoved = new ArrayList();
  }

  /**
   * Method to get total price paid
   * @return totalPricePaid
   */
  public double getTotalPricePaid() {
    return totalPricePaid;
  }

  /**
   * Method to set total price paid
   * @param totalPricePaid totalPricePaid
   */
  public void setTotalPricePaid(double totalPricePaid) {
    this.totalPricePaid = totalPricePaid;
  }

  /**
   * Method to get product list received by customer
   * @return productListCustomerReceived
   */
  public List<CartItem> getProductList() {
    return productListCustomerReceived;
  }

  /**
   * Method to set product list received by customer
   * @param productListCustomerReceived productListCustomerReceived
   */
  public void setProductList(List<CartItem> productListCustomerReceived) {
    this.productListCustomerReceived = productListCustomerReceived;
  }

  /**
   * Method to get product list out of stock
   * @return productListOutOfStock
   */
  public List<CartItem> getProductListOutOfStock() {
    return productListOutOfStock;
  }

  /**
   * Method to set product list out of stock
   * @param productListOutOfStock productListOutOfStock
   */
  public void setProductListOutOfStock(List<CartItem> productListOutOfStock) {
    this.productListOutOfStock = productListOutOfStock;
  }

  /**
   * Method to get product list removed due to age restriction
   * @return productListRemoved
   */
  public List<CartItem> getProductListRemoved() {
    return productListRemoved;
  }

  /**
   * Method to set product list removed due to age restriction
   * @param productListRemoved productListRemoved
   */
  public void setProductListRemoved(List<CartItem> productListRemoved) {
    this.productListRemoved = productListRemoved;
  }

  /**
   * Method to add products received by customers
   * @param cartItem cartItem
   */
  public void addToProductsReceivedByCustomer(CartItem cartItem) {
    this.productListCustomerReceived.add(cartItem);

    //updating bill price
    this.setTotalPricePaid(this.getTotalPricePaid() + (cartItem.getProduct().getPrice()
        * cartItem.getQuantity()));

  }

  /**
   * Method to add products removed by age restriction
   * @param cartItem cartItem
   */
  public void addToProductsRemoved(CartItem cartItem) {
    this.productListRemoved.add(cartItem);
  }

  /**
   * Method to add products removed due to out of stock
   * @param cartItem cartItem
   */
  public void addToProductsOutOfStock(CartItem cartItem) {
    this.productListOutOfStock.add(cartItem);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Receipt receipt = (Receipt) o;
    return Double.compare(receipt.totalPricePaid, totalPricePaid) == 0
        && Objects.equals(productListCustomerReceived, receipt.productListCustomerReceived)
        && Objects.equals(productListOutOfStock, receipt.productListOutOfStock)
        && Objects.equals(productListRemoved, receipt.productListRemoved);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPricePaid, productListCustomerReceived, productListOutOfStock,
        productListRemoved);
  }
}
