import java.util.Objects;

/**
 * Class to store Name fields
 */
public class Name {
  private String firstName;
  private String lastName;

  /**
   *Constructor for name object
   * @param firstName firstName
   * @param lastName lastName
   */
  public Name(String firstName, String lastName){
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Method to get first name
   * @return firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Method to set first name
   * @param firstName firstName
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Method to get last name
   * @return lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Method to set last name
   * @param lastName lastName
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Name name = (Name) o;
    return Objects.equals(firstName, name.firstName) && Objects.equals(lastName,
        name.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }
}
