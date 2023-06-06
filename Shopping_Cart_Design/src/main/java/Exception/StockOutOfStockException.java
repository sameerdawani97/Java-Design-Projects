package Exception;

/**
 * Exception class for out of stock exception
 */
public class StockOutOfStockException extends RuntimeException {
  /**
   * Constructor for customized exception class
   * @param message message
   */
  public StockOutOfStockException(String message) {
    super(message);
  }
}
