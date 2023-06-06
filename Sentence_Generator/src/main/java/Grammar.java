import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 *Class designed to store rules of the grammar in key value pairs.
 */
public class Grammar {

  private HashMap<String,Object> rules;
  private Random random;

  /**
   * variable to store the random seed
   */
  public static final int RANDOM_SEED=4;

  /**
   * Constructor for grammar object which stores rules for particular grammar or json file
   * @param rules rules
   */
  public Grammar(HashMap<String, Object> rules) {
    this.rules = rules;
    random= new Random(RANDOM_SEED);
  }

  /**
   * This method gives title of grammar
   * @return title of grammar
   */
  public String getGrammarTitle(){
    return (String)rules.get("grammarTitle");
  }

  /**
   * This method gives start of grammar
   * @return start of grammar
   */
  public List getGrammarStart(){
    return (List)rules.get("start");
  }

  /**
   * this method checks if key or NonTerminal word exists in grammar
   * @param key key
   * @return validate if it exists or not
   */
  public boolean checkRule(String key){
    if(this.rules.containsKey(key)){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * This method is to get random production from NonTerminal
   * @param rule rule
   * @return production at i
   */
  public String getRandomProduction(String rule){
    List<String> productions= (List<String>)this.rules.get(rule);
    int index= random.nextInt(productions.size());

    return productions.get(index);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Grammar grammar)) {
      return false;
    }
    return rules.equals(grammar.rules) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rules);
  }


  @Override
  public String toString() {
    return "Grammar{" +
        "rules=" + rules +
        '}';
  }
}
