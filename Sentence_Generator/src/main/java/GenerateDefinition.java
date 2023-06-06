import java.util.Objects;

/**
 * A class for generating definitions
 **/
public class GenerateDefinition {

  private Grammar grammar;

  /**
   * Represents left bracket
   */
  public static final String LEFT_BRACKET = "<";

  /**
   * Represents right bracket
   */
  public static final String RIGHT_BRACKET = ">";

  /**
   * Represents integer 0
   */
  public static final int ZERO = 0;

  /**
   * Represents integer 1
   */
  public static final int ONE = 1;

  /**
   * Constructor for class GenerateDefinition
   * @param grammar - A set of rules for languages.
   */
  public GenerateDefinition(Grammar grammar) {
    this.grammar = grammar;

  }

  /**
   * A method to return generated definitions
   * @param inputString inputString
   * @return definition, which consists of a non-terminal and its set of "productions" or "expansions".
   */
  public Definition getDefinition(String inputString){

    Definition definition;
    if(inputString.startsWith(LEFT_BRACKET) && inputString.endsWith(RIGHT_BRACKET)){
      String key = inputString.substring(ONE, inputString.length()-ONE);
      if(grammar.checkRule(key)){
        definition= new NonTerminalString(grammar.getRandomProduction(key));
      }
      else{
        throw new NonTerminalNotDefinedException(key);
      }
    }
    else if (inputString.length()==ONE && !Character.isLetter(inputString.charAt(ZERO))){
      definition= new SpecialCharacter(inputString);
    }
    else{
      definition= new TerminalString(inputString);
    }
    return definition;
  }

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
    if (!(o instanceof GenerateDefinition that)) {
      return false;
    }
    return grammar.equals(that.grammar);
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for the object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(grammar);
  }

  /**
   * Returns a hash code value for the object.
   * @return a hash code value for the object.
   */
  @Override
  public String toString() {
    return "GenerateDefinition{" +
        "grammar=" + grammar +
        '}';
  }
}
