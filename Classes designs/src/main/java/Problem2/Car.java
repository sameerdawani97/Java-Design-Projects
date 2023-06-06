package Problem2;

/**
 * Abstract Class for car which is inherited from vehicle
 */
public abstract class Car extends Vehicle{

  /**
   * default constructor
   */
    public Car(){
      super();
    }

  /**
   * Constructor of car with customized parameters.
   * @param vehicleId vehicleId of car
   * @param manufacturingYear manufacturingYear of car
   * @param makeModel MakeModel object of car
   * @param msrp msrp of car
   */
    public Car(String vehicleId, int manufacturingYear, MakeModel makeModel, double msrp){
    super(vehicleId, manufacturingYear, makeModel, msrp);
  }
}
