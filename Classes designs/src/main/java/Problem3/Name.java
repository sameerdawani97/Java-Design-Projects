package Problem3;

/**
 * Class to store values of Name including firstName, middleName and lastName.
 */
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;

  /**
   *
   * @param firstName firstName
   * @param middleName middleName
   * @param lastName lastName
   */
  public Name(String firstName, String middleName, String lastName){
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }


  /**
   * Method to get firstname
   * @return firstname
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Method to set firstname
   * @param firstName firstName
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Method to get middleName
   * @return middleName
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Method to set middleName
   * @param middleName middleName
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * Method to return lastName
   * @return lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Method to set firstname
   * @param lastName lastName
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
