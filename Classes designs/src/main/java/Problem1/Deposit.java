package Problem1;

/**
 * Exception for deposit out of range
 */
import Problem1.exceptions.customException;
import java.util.Objects;

/**
 * Class to store information of deposit including deposit amount, recipient details etc.
 */
public class Deposit {

  /**
   * Maximum amount for deposit is fixed 10000
   */
  public  static final int MAX_AMOUNT = 10000;

  /**
   * Minimum amount of deposit is fixed 1000
   */
  public  static final int MIN_AMOUNT = 1000;
  private int depositAmount;
  private  String accountId;
  private Name recipientName;

  /**
   * Default constructor
   */
  public Deposit(){

  }

  /**
   * Create new Deposit object give the deposit amount, accountId and recipient name.
   * @param depositAmount deposit amount
   * @param accountId accountId of flyer
   * @param recipientName name of recipient flyer
   * @throws customException due to invalid deposit amount
   */
  public Deposit(int depositAmount, String accountId, Name recipientName) throws customException{
    if (!checkDepositRange(depositAmount)){
      throw new customException("Invalid deposit...Deposit range should be between 1000 and 10000");
    }
    this.depositAmount = depositAmount;
    this.accountId = accountId;
    this.recipientName = recipientName;
  }

  /**
   * Method to verify range of amount for deposit.
   * @param depositAmount deposit amount for deposit
   * @return validity of amount in range
   */
  public boolean checkDepositRange(int depositAmount){
    if(depositAmount<MIN_AMOUNT || depositAmount > MAX_AMOUNT){
      return false;
    }
    return true;
  }

  /**
   * Method to get deposit amount
   * @return depositAmount
   */
  public int getDepositAmount() {
    return depositAmount;
  }

  /**
   * Method to set deposit amount
   * @param depositAmount depositAmount
   */
  public void setDepositAmount(int depositAmount) {
    this.depositAmount = depositAmount;
  }

  /**
   * Method to get accountId
   * @return accountId
   */
  public String getAccountId() {
    return accountId;
  }

  /**
   * Method to set accountId
   * @param accountId accountId
   */
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  /**
   * Method to get recipient name
   * @return recipientName
   */
  public Name getRecipientName() {
    return recipientName;
  }

  /**
   * Method to set recipient name
   * @param recipientName recipientName
   */
  public void setRecipientName(Name recipientName) {
    this.recipientName = recipientName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Deposit deposit = (Deposit) o;
    return depositAmount == deposit.depositAmount && Objects.equals(accountId,
        deposit.accountId) && Objects.equals(recipientName, deposit.recipientName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(depositAmount, accountId, recipientName);
  }
}
