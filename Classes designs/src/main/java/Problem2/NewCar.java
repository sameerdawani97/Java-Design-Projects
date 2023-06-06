package Problem2;

import java.util.Objects;

/**
 * Class of New car which is inherited from Car class
 */
public class NewCar extends Car{

  private int CountCars50miles;

  /**
   * default constructor
   * Create object of New car
   */
  public NewCar(){
    super();
  }


  /**
   * Create new car object given following parameters
   * @param vehicleId vehicleId
   * @param manufacturingYear manufacturing year
   * @param makeModel make and model object
   * @param msrp msrp
   * @param CountCars50miles count of cars with in 50 miles
   */
  public NewCar(String vehicleId, int manufacturingYear, MakeModel makeModel, double msrp, int CountCars50miles) {
    super(vehicleId, manufacturingYear, makeModel, msrp);
    this.CountCars50miles = CountCars50miles;
  }

  /**
   * Method to get count of cars with in 50 miles
   * @return countCars50miles
   */
  public int getCountCars50miles() {
    return CountCars50miles;
  }

  /**
   * Method to set count of cars with in 50 miles
   * @param countCars50miles count of cars within 50 miles
   */
  public void setCountCars50miles(int countCars50miles) {
    CountCars50miles = countCars50miles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewCar car = (NewCar) o;
    return CountCars50miles == car.CountCars50miles;
  }

  @Override
  public int hashCode() {
    return Objects.hash(CountCars50miles);
  }
}
