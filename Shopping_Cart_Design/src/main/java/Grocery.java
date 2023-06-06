import java.util.Objects;

/**
 * Abstract Class for Grocery inherited from Product
 */
public abstract class Grocery extends Product{
  private double weight;

  /**
   * Default constructor for Grocery
   */
  public Grocery(){
    super();
  }

  /**
   * Method to get weight of grocery product
   * @return weight
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Method to set weight of grocery product
   * @param weight weight
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Grocery grocery = (Grocery) o;
    return Double.compare(grocery.weight, weight) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), weight);
  }
}
