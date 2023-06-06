import java.util.Objects;

/**
 * Class Definition represents a string consists of a non-terminal and its set of "productions" or "expansions".
 * It can be terminal, non-terminal or special characters.
 **/
public abstract class Definition {

  private String definition;

  /**
   * Constructor for class Definition
   * @param definition - Consists of a non-terminal and its set of "productions" or "expansions".
   */
  public Definition(String definition) {
    this.definition = definition;
  }

  /**
   * Getter for Definition
   * @return definition, which consists of a non-terminal and its set of "productions" or "expansions".
   */
  public String getDefinition() {
    return this.definition;
  }

  /**
   * Generates production
   * @return definition, which consists of a non-terminal and its set of "productions" or "expansions".
   */
  public abstract String getProduction();

  /**
   * This method compares the equality of the current Name object with the object of the same type.
   * @param o - the Name object to be compared.
   * @return true if they are same, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Definition that = (Definition) o;
    return Objects.equals(definition, that.definition);
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for the object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(definition);
  }

  /**
   * Returns a string representation of the object.
   * @return  A string representation of the object.
   */
  @Override
  public String toString() {
    return "Definition{" +
        "definition='" + definition + '\'' +
        '}';
  }
}
