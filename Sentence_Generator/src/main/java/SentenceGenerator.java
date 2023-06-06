import java.util.Objects;

/**
 * Class to generate sentence from given grammar or rule
 **/
public class SentenceGenerator {

  private Grammar grammar;
  private GenerateDefinition generateDefinition;

  /**
   * variable to represent start string literal of json
   */
  public static final String START="<start>";

  /**
   * variable to store space string
   */
  public static final String SPACE=" ";


  /**
   * variable to represent empty string
   */
  public static final String EMPTY="";

  /**
   * Constructor for SentenceGenerator class
   * @param grammar grammar
   */
  public SentenceGenerator(Grammar grammar) {
    this.grammar = grammar;
    generateDefinition = new GenerateDefinition(this.grammar);
  }

  /**
   * This method is actually generating sentence and calls generate function to generate output recursively for NonTerminals
   * @return sentence without spaces at start or end
   */
  public String generateSentence(){
    String result= generate(START);
    // Strip to remove spaces from start and end of string
    return result.strip();
  }

  /**
   * This method generates sequence recursively by passing string sequence
   * @param sequence sequence
   * @return produced sentence string
   */
  private String generate(String sequence){

    String res=EMPTY;
    for(String word:sequence.strip().split(SPACE)){
      Definition definition= generateDefinition.getDefinition(word);
      String production=definition.getProduction();
      if(definition.getClass().getName()==NonTerminalString.class.getName()){
        res= res.concat( generate(production));
      }
      else{
        res=res.concat(production);
      }
    }
    return res;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SentenceGenerator that)) {
      return false;
    }
    return grammar.equals(that.grammar) && generateDefinition.equals(that.generateDefinition);
  }


  @Override
  public int hashCode() {
    return Objects.hash(grammar, generateDefinition);
  }


  @Override
  public String toString() {
    return "SentenceGenerator{" +
        "grammar=" + grammar +
        ", generateDefinition=" + generateDefinition +
        '}';
  }


}
