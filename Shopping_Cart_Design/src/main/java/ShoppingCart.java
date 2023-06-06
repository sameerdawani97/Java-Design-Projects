import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for shopping cart of customer
 */
public class ShoppingCart {

  private List<CartItem> cart;

  private Double totalCostOfCart;
  private Receipt receipt;
  private final static double DEFAULT_CART_COST = 0;

  /**
   * Constructor for shopping cart
   */
  public ShoppingCart(){
    this.cart = new ArrayList<CartItem>();
    this.totalCostOfCart = DEFAULT_CART_COST;
  }

  /**
   * Method to get cart list containing cart items of customer
   * @return cart
   */
  public List<CartItem> getCart() {
    return cart;
  }

  /**
   * Method to set cart
   * @param cart cart
   */
  public void setCart(List<CartItem> cart) {
    this.cart = cart;
  }

  /**
   * Method to get total cost of cart
   * @return totalCostOfCart
   */
  public Double getTotalCostOfCart() {
    return totalCostOfCart;
  }

  /**
   * Method to set totalCost of cart
   * @param totalCostOfCart totalCostOfCart
   */
  public void setTotalCostOfCart(Double totalCostOfCart) {
    this.totalCostOfCart = totalCostOfCart;
  }

  /**
   * Method to get receipt of cart
   * @return receipt
   */
  public Receipt getReceipt() {
    return receipt;
  }

  /**
   * Method to set receipt
   * @param receipt receipt
   */
  public void setReceipt(Receipt receipt) {
    this.receipt = receipt;
  }

  /**
   * Method to add cart item into cart
   * @param cartItem cartItem
   */
  public void addProductToCart(CartItem cartItem){
    boolean itemExist = false;
    for(CartItem item : this.cart){
      if(item.getProduct().equals(cartItem.getProduct())){
        itemExist = true;
        int index = this.cart.indexOf(item);
        int quantity = this.cart.get(index).getQuantity() + cartItem.getQuantity();
        this.cart.get(index).setQuantity(quantity);
      }
    }

    if(!itemExist){
      this.cart.add(cartItem);
    }
    double productCost = cartItem.getProduct().getPrice()*cartItem.getQuantity();
    updateCartValue(productCost);


  }

  /**
   * Method to update cart value
   * @param value value
   */
  public void updateCartValue(Double value){
    setTotalCostOfCart(this.totalCostOfCart+value);
  }

  /**
   * Method to add product list customer received to receipt
   * @param cartItem cartItem
   */
  public void addProductListCustomerReceived(CartItem cartItem){
    receipt.addToProductsReceivedByCustomer(cartItem);
  }

  /**
   * Method to add product list out of stock in receipt
   * @param cartItem cartItem
   */
  public void addProductListOutOfStock(CartItem cartItem){
    receipt.addToProductsOutOfStock(cartItem);
  }

  /**
   * Method to add product list removed due to age restriction in receipt
   * @param cartItem cartItem
   */
  public void addProductListRemoved(CartItem cartItem){
    receipt.addToProductsRemoved(cartItem);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShoppingCart that = (ShoppingCart) o;
    return Objects.equals(cart, that.cart) && Objects.equals(totalCostOfCart,
        that.totalCostOfCart) && Objects.equals(receipt, that.receipt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cart, totalCostOfCart, receipt);
  }
}
