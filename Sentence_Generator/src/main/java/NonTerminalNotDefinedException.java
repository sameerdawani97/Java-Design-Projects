/**
 * Thrown an exception when a non-terminal is used but not defined in the grammar
 */
public class NonTerminalNotDefinedException extends RuntimeException {

  /**
   * Error message when a non-terminal is used but not defined in the grammar
   */
  public static final String MESSAGE = "The Non-Terminal is not defined in the grammar : ";

  /**
   * Constructs a new runtime exception with the specified detail message. The cause is not
   * initialized, and may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param nonTerminalString the detail message. The detail message is saved for later retrieval by the
   *                    {@link #getMessage()} method.
   */
  public NonTerminalNotDefinedException(String nonTerminalString) {
    super(MESSAGE + nonTerminalString);
  }
}
