package Problem1.exceptions;

/**
 * custom exception class for customized message
 */
public class customException extends RuntimeException{

  /**
   * Constructor for customized exception class
   * @param message message
   */
  public customException(String message) {
    super(message);
  }
}
