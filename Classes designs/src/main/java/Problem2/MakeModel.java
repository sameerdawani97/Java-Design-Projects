package Problem2;

import java.util.Objects;

/**
 * Class for make and model.
 */
public class MakeModel {
  private String vehicleMake;
  private String vehicleModel;

  /**
   * Creates the MakeModel object given vehicle make and vehicle model.
   * @param vehicleMake It is String value for vehicle make.
   * @param vehicleModel It is String value for vehicle model.
   */
  public MakeModel(String vehicleMake, String vehicleModel){
    this.vehicleMake = vehicleMake;
    this.vehicleModel = vehicleModel;

  }

  /**
   * Method to get make of vehicle.
   * @return Vehicle Make
   */
  public String getVehicleMake() {
    return vehicleMake;
  }

  /**
   * Method to set make of vehicle.
   * @param vehicleMake VehicleMake is string variable for vehicle make.
   */
  public void setVehicleMake(String vehicleMake) {
    this.vehicleMake = vehicleMake;
  }

  /**
   * Method to get model of vehicle.
   * @return Vehicle Model
   */
  public String getVehicleModel() {
    return vehicleModel;
  }

  /**
   * Method to set model of vehicle.
   * @param vehicleModel VehicleModel is string variable for vehicle model.
   */
  public void setVehicleModel(String vehicleModel) {
    this.vehicleModel = vehicleModel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MakeModel makeModel = (MakeModel) o;
    return Objects.equals(vehicleMake, makeModel.vehicleMake) && Objects.equals(
        vehicleModel, makeModel.vehicleModel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicleMake, vehicleModel);
  }
}
