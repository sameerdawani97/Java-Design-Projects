package Problem2;

/**
 * Abstract class for vehicle.
 */
public abstract class Vehicle {
  private String vehicleId;
  private int manufacturingYear;
  private MakeModel makeModel;
  private double msrp;

  /**
   * default constructor
   */
  public Vehicle(){

  }
  /**
   * Constructor method with parameters given.
   * @param vehicleId It is unique vehicle id for each vehicle.
   * @param manufacturingYear It is manufacturing year for vehicle.
   * @param makeModel It is makeModel object which indicates make and model of vehicle.
   * @param msrp It is manufacturer suggested retail price of vehicle.
   */
  public Vehicle(String vehicleId, int manufacturingYear, MakeModel makeModel, double msrp){
    this.makeModel = makeModel;
    this.vehicleId = vehicleId;
    this.manufacturingYear = manufacturingYear;
    this.msrp = msrp;
  }

  /**
   * Method to get vehicleId of vehicle
   * @return vehicleId
   */
  public String getVehicleId() {
    return vehicleId;
  }

  /**
   * Method to set vehicleId of vehicle
   * @param vehicleId vehicleId
   */
  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  /**
   * Method to return manufacturing year of vehicle
   * @return manufacturingYear
   */
  public int getManufacturingYear() {
    return manufacturingYear;
  }

  /**
   * Method to set manufacturing year of vehicle
   * @param manufacturingYear manufacturingYear
   */
  public void setManufacturingYear(int manufacturingYear) {
    this.manufacturingYear = manufacturingYear;
  }

  /**
   * Method to return MakeModel object of vehicle which contains make and model
   * @return MakeModel object
   */
  public MakeModel getMakeModel() {
    return makeModel;
  }

  /**
   * Method to set makeModel object in vehicle
   * @param makeModel make and model of vehicle
   */
  public void setMakeModel(MakeModel makeModel) {
    this.makeModel = makeModel;
  }


  /**
   * Method to get msrp of car
   * @return msrp
   */
  public double getMsrp() {
    return msrp;
  }

  /**
   * Method to set msrp of car
   * @param msrp msrp of car
   */
  public void setMsrp(double msrp) {
    this.msrp = msrp;
  }
}
