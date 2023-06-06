import java.util.Objects;

/**
 * Abstract Class for Household inherited from Product
 */
public abstract class HouseHold extends Product{

  private int numOfUnits;

  /**
   * Default constructor for HouseHold
   */
  public HouseHold(){
    super();
  }

  /**
   * Method to get num of units in a product
   * @return numOfUnits
   */
  public int getNumOfUnits() {
    return numOfUnits;
  }

  /**
   * Method to set num of units in a product
   * @param numOfUnits numOfUnits
   */
  public void setNumOfUnits(int numOfUnits) {
    this.numOfUnits = numOfUnits;
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
    HouseHold houseHold = (HouseHold) o;
    return numOfUnits == houseHold.numOfUnits;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numOfUnits);
  }
}
