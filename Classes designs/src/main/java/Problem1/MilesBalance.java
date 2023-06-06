package Problem1;

import java.util.Objects;

/**
 * Class to store information of MilesBalance including total miles,
 * earned miles this year, expiring miles by the end of this calendar year.
 */
public class MilesBalance {
  private int totalMiles;
  private int earnedMiles;
  private int expiringMiles;

  /**
   * Create MilesBalance object given total miles, earned miles and expiring miles
   * @param totalMiles totalMiles
   * @param earnedMiles earned miles
   * @param expiringMiles expiring miles this calendar year
   */
  public MilesBalance(int totalMiles, int earnedMiles, int expiringMiles){
    this.totalMiles = totalMiles;
    this.earnedMiles = earnedMiles;
    this.expiringMiles = expiringMiles;
  }

  /**
   * Method to return total miles flyer has
   * @return totalMiles
   */
  public int getTotalMiles() {
    return totalMiles;
  }

  /**
   * Method to set total miles in MilesBalance object
   * @param totalMiles totalMiles
   */
  public void setTotalMiles(int totalMiles) {
    this.totalMiles = totalMiles;
  }

  /**
   * Method to return earnedMiles of MilesBalance
   * @return earnedMiles
   */
  public int getEarnedMiles() {
    return earnedMiles;
  }

  /**
   * Method to set earned miles in MilesBalance object
   * @param earnedMiles earnedMiles
   */
  public void setEarnedMiles(int earnedMiles) {
    this.earnedMiles = earnedMiles;
  }

  /**
   * Method to return miles expiring by the end of this calendar year
   * @return expiringMiles
   */
  public int getExpiringMiles() {
    return expiringMiles;
  }

  /**
   * Method to set expiring miles in MilesBalance object
   * @param expiringMiles expiringMiles by the end of this calendar year
   */
  public void setExpiringMiles(int expiringMiles) {
    this.expiringMiles = expiringMiles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MilesBalance that = (MilesBalance) o;
    return totalMiles == that.totalMiles && earnedMiles == that.earnedMiles
        && expiringMiles == that.expiringMiles;
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalMiles, earnedMiles, expiringMiles);
  }
}
