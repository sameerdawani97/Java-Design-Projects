import java.util.Objects;

/**
 * Abstract Class for Product.
 */
public abstract class Product {

  private String manufacturer;
  private String productName;
  private int price;
  private int minimumAge;
  private final static int DEFAULT_MIN_AGE = 0;

  /**
   * Default Constructor for Product class
   */
  public Product(){
    this.minimumAge = DEFAULT_MIN_AGE;
  }

  /**
   * Method to get manufacturer of product
   * @return manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Method to set manufacturer
   * @param manufacturer manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Method to get productName of product
   * @return productName
   */
  public String getProductName() {
    return productName;
  }

  /**
   * Method to set productName
   * @param productName productName
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * Method to get price of product
   * @return price
   */
  public int getPrice() {
    return price;
  }

  /**
   * Method to set price
   * @param price price
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * Method to get Minimum age of product
   * @return minimumAge
   */
  public int getMinimumAge() {
    return minimumAge;
  }

  /**
   * Method to set minimumAge
   * @param minimumAge minimumAge
   */
  public void setMinimumAge(int minimumAge) {
    this.minimumAge = minimumAge;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return price == product.price && minimumAge == product.minimumAge && Objects.equals(
        manufacturer, product.manufacturer) && Objects.equals(productName,
        product.productName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(manufacturer, productName, price, minimumAge);
  }
}
