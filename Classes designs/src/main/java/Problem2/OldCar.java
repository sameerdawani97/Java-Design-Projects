package Problem2;

/**
 * Class for old car which inherited from car class
 */
public class OldCar extends Car {

  private int mileage;
  private int numberOfPreviousOwners;
  private int numberOfMinorAccidents;

  /**
   * Default constructor to create object of Old car
   */
  public OldCar(){
    super();
  }

  /**
   * Constructor to create OldCar object given following parameters
   * @param vehicleId vehicleId
   * @param manufacturingYear manufacturing year
   * @param makeModel make and model object
   * @param msrp msrp
   * @param mileage mileage of car
   * @param numberOfPreviousOwners numberOfPreviousOwners
   * @param numberOfMinorAccidents numberOfMinorAccidents
   */
  public OldCar(String vehicleId, int manufacturingYear, MakeModel makeModel, double msrp, int mileage,
      int numberOfPreviousOwners, int numberOfMinorAccidents) {
    super(vehicleId, manufacturingYear, makeModel, msrp);
    this.mileage = mileage;
    this.numberOfPreviousOwners = numberOfPreviousOwners;
    this.numberOfMinorAccidents = numberOfMinorAccidents;
  }

  /**
   * Method to get mileage of old car
   * @return mileage
   */
  public int getMileage() {
    return mileage;
  }

  /**
   * Method to set mileage of old car
   * @param mileage mileage
   */
  public void setMileage(int mileage) {
    this.mileage = mileage;
  }

  /**
   * Method to get number of previous owners of old car
   * @return numberOfPreviousOwners
   */
  public int getNumberOfPreviousOwners() {
    return numberOfPreviousOwners;
  }

  /**
   * Method to set number of previous owners of old car
   * @param numberOfPreviousOwners numberOfPreviousOwners
   */
  public void setNumberOfPreviousOwners(int numberOfPreviousOwners) {
    this.numberOfPreviousOwners = numberOfPreviousOwners;
  }

  /**
   * Method to get number of Minor Accidents of old car
   * @return numberOfMinorAccidents
   */
  public int getNumberOfMinorAccidents() {
    return numberOfMinorAccidents;
  }

  /**
   * Method to set number of Minor Accidents of old car
   * @param numberOfMinorAccidents numberOfMinorAccidents
   */
  public void setNumberOfMinorAccidents(int numberOfMinorAccidents) {
    this.numberOfMinorAccidents = numberOfMinorAccidents;
  }
}
