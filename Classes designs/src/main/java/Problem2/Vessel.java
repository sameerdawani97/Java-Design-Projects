package Problem2;

/**
 * Abstract Class for vessel which is inherited from vehicle
 */
public abstract class Vessel extends Vehicle{

  /**
   * default constructor
   */
  public Vessel(){
    super();
  }


  /**
   * Constructor of vessel with customized parameters.
   * @param vehicleId vehicleId of car
   * @param manufacturingYear manufacturingYear of car
   * @param makeModel MakeModel object of car
   * @param msrp msrp of car
   */
  public Vessel(String vehicleId, int manufacturingYear, MakeModel makeModel, double msrp){
    super(vehicleId, manufacturingYear, makeModel, msrp);
  }

}
