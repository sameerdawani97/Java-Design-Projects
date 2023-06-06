package Problem1;

import Problem1.exceptions.customException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Flyer class to store details of flyer including its accountId, name, emailId and milesBalance
 */

public class Flyer {

  /**
   * ACCOUNT_ID LENGTH IS FIXED 12
   */
  public static final int ACCOUNT_ID_LENGTH = 12;
  private String accountId;
  private String emailId;
  private MilesBalance milesBalance;
  private Name name;
  private FlyerStore flyerStore;


  /**
   * Default constructor
   */
  public Flyer(){

  }

  /**
   * Creates a new Flyer given the accountId, name, email, milesBalance and flyerStore.
   *
   * @param accountId accountId of Flyer
   * @param emailId emailId of flyer
   * @param milesBalance milesBalance of flyer
   * @param name name of flyer
   * @param flyerStore pass flyerStore hashmap to check existence of flyer object.
   */
  public Flyer(String accountId, String emailId, MilesBalance milesBalance, Name name, FlyerStore flyerStore){
    if(accountId.length()!=ACCOUNT_ID_LENGTH){
      throw new customException("AccountId length should be 12..!");
    }
    else if(flyerStore.getFlyerStore().containsKey(accountId)){
      throw new customException("Account already exists..!");
    }
    else{
      this.accountId = accountId;
      this.name = name;
      this.emailId = emailId;
      this.milesBalance = milesBalance;
      this.flyerStore = flyerStore;
    }


  }

  /**
   * Method to transfer miles to another flyer.
   * @param deposit deposit object.
   */
  public void transferMiles(Deposit deposit){

      if(privacyCheck(deposit.getAccountId(),deposit.getRecipientName())){
        int depositAmount = deposit.getDepositAmount();
        if(!validateAmount(deposit)) {
          throw new customException("Deposit miles are greater than miles available..!");
        }
        Flyer recipient = flyerStore.getFlyerStore().get(deposit.getAccountId());
        MilesBalance recipientMilesBalance = recipient.getMilesBalance();

        recipientMilesBalance.setTotalMiles(recipientMilesBalance.getTotalMiles() + depositAmount);
        recipientMilesBalance.setEarnedMiles(recipientMilesBalance.getEarnedMiles() + depositAmount);
        recipientMilesBalance.setExpiringMiles(recipientMilesBalance.getExpiringMiles() + depositAmount);
        this.milesBalance.setTotalMiles(this.milesBalance.getTotalMiles() - depositAmount);
        this.milesBalance.setEarnedMiles(this.milesBalance.getEarnedMiles() - depositAmount);
        this.milesBalance.setExpiringMiles(this.milesBalance.getExpiringMiles() - depositAmount);

      }
      else{
        throw new customException("Recipient details are incorrect..!");
      }
  }

  /**
   * Method to check amount is sufficient to transfer.
   * @param deposit deposit object.
   * @return validity validation that amount is valid for transfer.
   */
  public boolean validateAmount(Deposit deposit){
    int depositAmount = deposit.getDepositAmount();
    if(depositAmount > this.milesBalance.getTotalMiles()) {
      return false;
    }
    return true;
  }
  /**
   * Method to check that accountId and name belongs to same object.
   * @param accountId accountId of flyer to check
   * @param name name of flyer to check
   * @return privacyCheck
   */
  public boolean privacyCheck(String accountId, Name name){
    if(flyerStore.getFlyerStore().containsKey(accountId)) {
      Flyer recipient = flyerStore.getFlyerStore().get(accountId);
      return recipient.getName().getFirstName() == name.getFirstName() &&
          recipient.getName().getMiddleName()==name.getMiddleName() &&
          recipient.getName().getLastName()==name.getLastName();
    } else {
      return false;
    }
  }

  /**
   * Method to return accountId.
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
   * Method to get emailId of flyer
   * @return emailId
   */
  public String getEmailId() {
    return emailId;
  }

  /**
   * Method to set emailId of flyer.
   * @param emailId emailId
   */
  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  /**
   * Method to return mileBalance object.
   * @return milesBalance
   */
  public MilesBalance getMilesBalance() {
    return milesBalance;
  }

  /**
   * Method to set mileBalance object to flyer.
   * @param milesBalance mileBalance
   */
  public void setMilesBalance(MilesBalance milesBalance) {
    this.milesBalance = milesBalance;
  }

  /**
   * Method to return name object containing firstName, middleName, lastName.
   * @return name
   */
  public Name getName() {
    return name;
  }

  /**
   * Method to set name object to flyer.
   * @param name name object
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * Method to get flyerStore
   * @return flyerStore
   */
  public FlyerStore getFlyerStore() {
    return flyerStore;
  }

  /**
   * Method to set flyerStore
   * @param flyerStore flyerStore
   */
  public void setFlyerStore(FlyerStore flyerStore) {
    this.flyerStore = flyerStore;
  }



}
