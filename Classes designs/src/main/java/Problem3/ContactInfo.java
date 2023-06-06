package Problem3;

/**
 * Class for contact information of tax filer
 */
public class ContactInfo {
  private Name name;
  private String address;
  private String phoneNumber;
  private String email;


  /**
   * Method to get Name of filer
   * @return Name
   */
  public Name getName() {
    return name;
  }

  /**
   * Method to set Name of filer
   * @param name name of filer
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * Method to get address of filer
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Method to set address of filer
   * @param address address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Method to get Phone number of filer
   * @return phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Method to set phone number
   * @param phoneNumber phoneNumber
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Method to get email of filer
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Method to set email of filer
   * @param email email of filer
   */
  public void setEmail(String email) {
    this.email = email;
  }
}
