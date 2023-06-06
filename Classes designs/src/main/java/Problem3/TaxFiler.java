package Problem3;

import java.util.Objects;

/**
 * Abstract class for tax filer
 */
public abstract class TaxFiler {

  private String taxId;
  private ContactInfo contactInfo;
  private Double lastYearEarning;
  private Double TaxAlreadyPaid;
  private Double mortgageInterestPaid;
  private Double propertyTaxesPaid;
  private Double studentLoanTuitionPaid;
  private Double retirementSavingAccount;
  private Double healthSavingAccount;
  private Double donationContribution;

  /**
   * Max income for property tax and interest is 250000
   */
  public  static final int MAX_INCOME_FOR_PROPERTY = 250000;
  /**
   * Min property tax paid should be 12500
   */
  public  static final int MIN_PROPERTY_TAX = 12500;
  /**
   * property tax exemption of 2500
   */
  public  static final int PROPERTY_TAX_EXEMPTION = 2500;


  /**
   * Method to calculate tax which applies for every tax filer
   * @return tax amount
   */
  protected  double calculateTax(){
    double taxForEveryone = this.lastYearEarning - this.TaxAlreadyPaid;
    if (this.lastYearEarning<MAX_INCOME_FOR_PROPERTY && (this.mortgageInterestPaid+this.propertyTaxesPaid)>MIN_PROPERTY_TAX){
      taxForEveryone = taxForEveryone - PROPERTY_TAX_EXEMPTION;
    }
    return taxForEveryone;
  }

  /**
   * Method to get taxId of taxFiler
   * @return taxId
   */
  public String getTaxId() {
    return taxId;
  }

  /**
   * Method to set taxId of tax filer
   * @param taxId taxId of filer
   */
  public void setTaxId(String taxId) {
    this.taxId = taxId;
  }

  /**
   * Method to get contact info of filer
   * @return contactInfo object
   */
  public ContactInfo getContactInfo() {
    return contactInfo;
  }

  /**
   * Method to set contactInfo object on filer object
   * @param contactInfo contactInfo object
   */
  public void setContactInfo(ContactInfo contactInfo) {
    this.contactInfo = contactInfo;
  }

  /**
   * Method to get getLastYearEarning of filer
   * @return getLastYearEarning
   */
  public Double getLastYearEarning() {
    return lastYearEarning;
  }

  /**
   * Method to set lastYearEarning of filer
   * @param lastYearEarning lastYearEarning
   */
  public void setLastYearEarning(Double lastYearEarning) {
    this.lastYearEarning = lastYearEarning;
  }

  /**
   * Method to get tax already paid by filer
   * @return TaxAlreadyPaid
   */
  public Double getTaxAlreadyPaid() {
    return TaxAlreadyPaid;
  }

  /**
   * Method to set tax already paid by filer
   * @param taxAlreadyPaid taxAlreadyPaid
   */
  public void setTaxAlreadyPaid(Double taxAlreadyPaid) {
    TaxAlreadyPaid = taxAlreadyPaid;
  }

  /**
   * Method to getMortgageInterestPaid by filer
   * @return mortgageInterestPaid
   */
  public Double getMortgageInterestPaid() {
    return mortgageInterestPaid;
  }

  /**
   * Method to set mortgageInterestPaid by filer
   * @param mortgageInterestPaid mortgageInterestPaid
   */
  public void setMortgageInterestPaid(Double mortgageInterestPaid) {
    this.mortgageInterestPaid = mortgageInterestPaid;
  }

  /**
   * Method to get property taxes paid by filer
   * @return propertyTaxesPaid
   */
  public Double getPropertyTaxesPaid() {
    return propertyTaxesPaid;
  }

  /**
   * Method to set propertyTaxesPaid by filer
   * @param propertyTaxesPaid propertyTaxesPaid
   */
  public void setPropertyTaxesPaid(Double propertyTaxesPaid) {
    this.propertyTaxesPaid = propertyTaxesPaid;
  }


  /**
   * Method to get student loan and tuition paid amount of filer
   * @return studentLoanTuitionPaid
   */
  public Double getStudentLoanTuitionPaid() {
    return studentLoanTuitionPaid;
  }

  /**
   * Method to set Student Loan and Tuition Paid by filer
   * @param studentLoanTuitionPaid studentLoanTuitionPaid
   */
  public void setStudentLoanTuitionPaid(Double studentLoanTuitionPaid) {
    this.studentLoanTuitionPaid = studentLoanTuitionPaid;
  }

  /**
   * Method to get Retirement savings account amount
   * @return retirementSavingAccount
   */
  public Double getRetirementSavingAccount() {
    return retirementSavingAccount;
  }

  /**
   * Method to set Retirement savings account amount
   * @param retirementSavingAccount retirementSavingAccount
   */
  public void setRetirementSavingAccount(Double retirementSavingAccount) {
    this.retirementSavingAccount = retirementSavingAccount;
  }

  /**
   * Method to get health and saving account amount
   * @return healthSavingAccount
   */
  public Double getHealthSavingAccount() {
    return healthSavingAccount;
  }

  /**
   * Method to set health and saving account amount
   * @param healthSavingAccount healthSavingAccount
   */
  public void setHealthSavingAccount(Double healthSavingAccount) {
    this.healthSavingAccount = healthSavingAccount;
  }

  /**
   * Method to get donation contribution amount
   * @return donationContribution
   */
  public Double getDonationContribution() {
    return donationContribution;
  }

  /**
   * Method to set donation contribution amount
   * @param donationContribution donationContribution
   */
  public void setDonationContribution(Double donationContribution) {
    this.donationContribution = donationContribution;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaxFiler taxFiler = (TaxFiler) o;
    return Objects.equals(taxId, taxFiler.taxId) && Objects.equals(contactInfo,
        taxFiler.contactInfo) && Objects.equals(lastYearEarning, taxFiler.lastYearEarning)
        && Objects.equals(TaxAlreadyPaid, taxFiler.TaxAlreadyPaid)
        && Objects.equals(mortgageInterestPaid, taxFiler.mortgageInterestPaid)
        && Objects.equals(propertyTaxesPaid, taxFiler.propertyTaxesPaid)
        && Objects.equals(studentLoanTuitionPaid, taxFiler.studentLoanTuitionPaid)
        && Objects.equals(retirementSavingAccount, taxFiler.retirementSavingAccount)
        && Objects.equals(healthSavingAccount, taxFiler.healthSavingAccount)
        && Objects.equals(donationContribution, taxFiler.donationContribution);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxId, contactInfo, lastYearEarning, TaxAlreadyPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanTuitionPaid, retirementSavingAccount, healthSavingAccount,
        donationContribution);
  }
}

