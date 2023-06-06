package Problem1;

import java.util.HashMap;
import java.util.Objects;

/**
 * Class to store all existing flyers.
 */
public class FlyerStore {

  private HashMap<String, Flyer> flyerStore;

  /**
   * Constructor of FlyerStore that creates hashmap for flyer objects.
   */
  public FlyerStore(){
    /**
     * Hashmap with key as accountId and value as respective Flyer object.
     */
    this.flyerStore = new HashMap<String,Flyer>();
  }

  /**
   * Method to create new flyer object
   * @param accountId accountId for new flyer object
   * @param emailId emailId for new flyer object
   * @param milesBalance mileBalance for new flyer object.
   * @param name name for new flyer object.
   * @param flyerStore flyerStore of flyer objects.
   * @return flyer object.
   */
  public Flyer createFlyer(String accountId, String emailId, MilesBalance milesBalance,
      Name name, FlyerStore flyerStore){
    Flyer flyer = new Flyer(accountId,emailId,milesBalance,name,flyerStore);
    HashMap<String,Flyer> flyerObjects = flyerStore.getFlyerStore();
    flyerObjects.put(accountId,flyer);
    flyerStore.setFlyerStore(flyerObjects);


    return flyer;
  }

  /**
   * Method to get flyer Store of flyer objects
   * @return flyerStore
   */
  public HashMap<String, Flyer> getFlyerStore() {
    return flyerStore;
  }

  /**
   * Method to set Flyer store
   * @param flyerStore flyerStore
   */
  public void setFlyerStore(HashMap<String, Flyer> flyerStore) {
    this.flyerStore = flyerStore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FlyerStore that = (FlyerStore) o;
    return Objects.equals(flyerStore, that.flyerStore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flyerStore);
  }
}
