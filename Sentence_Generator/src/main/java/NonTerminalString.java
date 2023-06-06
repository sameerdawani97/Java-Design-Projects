/**
 * Class NonTerminalString is to identify all the non-terminal elements which is a placeholder
 * that will expand to another sequence of words.
 */
public class NonTerminalString extends Definition {

  /**
   * Constructor for class NonTerminalString
   * @param definition - Consists of a non-terminal and its set of "productions" or "expansions".
   */
  public NonTerminalString(String definition) {
    super(definition);
  }

  /**
   * Gets the definition
   * @return definition, which consists of a non-terminal and its set of "productions" or "expansions".
   */
  @Override
  public String getDefinition() {
    return super.getDefinition();
  }

  /**
   * Generates production
   * @return definition, which consists of a non-terminal and its set of "productions" or "expansions".
   */
  @Override
  public String getProduction() {
    return this.getDefinition();
  }

  /**
   * This method compares the equality of the current Name object with the object of the same type.
   * @param object - the Name object to be compared.
   * @return true if they are same, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for the object.
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for the object.
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
