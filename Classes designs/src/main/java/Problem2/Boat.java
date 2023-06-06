package Problem2;

import java.util.Objects;

/**
 * Class of boat which is inherited from Vessel class
 */
public class Boat extends Vessel{
  private float boatLength;
  private int numberOfPassengers;
  private PropulsionType propulsionType;

  /**
   * Default constructor for boat object
   */
  public Boat(){
    super();
  }

  /**
   * Constructor to create object with given parameters
   * @param vehicleId vehicleId
   * @param manufacturingYear manufacturingYear
   * @param makeModel make and model object
   * @param msrp msrp
   * @param boatLength boat length
   * @param numberOfPassengers numberOfPassengers
   */
  public Boat(String vehicleId, int manufacturingYear, MakeModel makeModel, double msrp, float boatLength,
      int numberOfPassengers) {
    super(vehicleId, manufacturingYear, makeModel, msrp);
    this.boatLength = boatLength;
    this.numberOfPassengers = numberOfPassengers;
  }

  /**
   * Method to get length of boat
   * @return boatLength
   */
  public float getBoatLength() {
    return boatLength;
  }

  /**
   * Method to set length of boat
   * @param boatLength boatLength
   */
  public void setBoatLength(float boatLength) {
    this.boatLength = boatLength;
  }

  /**
   * Method to get number of passengers
   * @return numberOfPassengers
   */
  public int getNumberOfPassengers() {
    return numberOfPassengers;
  }

  /**
   * Method to set number of Passengers
   * @param numberOfPassengers numberOfPassengers
   */
  public void setNumberOfPassengers(int numberOfPassengers) {
    this.numberOfPassengers = numberOfPassengers;
  }

  /**
   * Method to get propulsion type of boat
   * @return propulsionType
   */
  public PropulsionType getPropulsionType() {
    return propulsionType;
  }

  /**
   * Method to set propulsionType of boat
   * @param propulsionType propulsionType
   */
  public void setPropulsionType(PropulsionType propulsionType) {
    this.propulsionType = propulsionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Boat boat = (Boat) o;
    return Float.compare(boat.boatLength, boatLength) == 0
        && numberOfPassengers == boat.numberOfPassengers && propulsionType == boat.propulsionType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(boatLength, numberOfPassengers, propulsionType);
  }
}
