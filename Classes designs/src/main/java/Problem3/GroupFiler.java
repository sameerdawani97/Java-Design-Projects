package Problem3;

import java.util.Objects;

/**
 * Abstract class for Group filer which is inherited by TaxFiler
 */
public abstract class GroupFiler extends TaxFiler{
  private int numberOfDependents;
  private int numberOfMinorChildren;
  private double childcareExpense;
  private double dependentCareExpense;
  /**
   * To represent zero taxable income
   */
  public  static final int ZERO_TAXABLE_INCOME = 0;
  /**
   * health and retirement exemption can be maximum 17500
   */
  public  static final int HEALTH_AND_RETIREMENT_MAX_AMOUNT = 17500;
  /**
   * taxable income lower range is upto 55000
   */
  public  static final int TAXABLE_INCOME_LOWER_RANGE = 90000;
  /**
   * Childcare expense should be at least 5000
   */
  public  static final int CHILDCARE_EXPENSE_MIN = 5000;
  /**
   * Income for having childcare expense should be less than 200000
   */
  public  static final int MAX_INCOME_FOR_CHILDCARE_EXPENSE = 200000;
  /**
   * Child care exemption is fixed 1250
   */
  public  static final int CHILDCARE_EXPENSE_EXEMPTION = 1250;
  /**
   * health and retirement calculating ratio
   */
  public  static final double HEALTH_AND_RETIREMENT_CAL_RATIO = 0.65;
  /**
   * lower taxable income tax calculating by multiplying with this ratio
   */
  public  static final double TAXABLE_INCOME_LOWER_RANGE_RATIO = 0.145;
  /**
   * higher taxable income tax calculating by multiplying with this ratio
   */
  public  static final double TAXABLE_INCOME_UPPER_RANGE_RATIO = 0.185;
  /**
   * Initial tax amount is fixed 0
   */
  public  static final double INITIAL_TAX_AMOUNT = 0;
  /**
   * Default constructor of group filer
   */
  public GroupFiler(){
    super();
  }



  /**
   * Method to calculate health and retirement amount exemption.
   * @param healthAndRetirement healthAndRetirement
   * @return double value for exemption amount
   */
  public double calculateHealthAndRetirementExemption(double healthAndRetirement){
    if (healthAndRetirement>HEALTH_AND_RETIREMENT_MAX_AMOUNT){
      healthAndRetirement = HEALTH_AND_RETIREMENT_MAX_AMOUNT;
    }
    return healthAndRetirement;
  }

  /**
   * Method to calculate taxable income in case it becomes less than zero
   *  @param taxableIncome taxableIncome
   *  @return taxable Income
   */
  public double setTaxableIncomeZero(double taxableIncome){
    if (taxableIncome<ZERO_TAXABLE_INCOME){
      taxableIncome = ZERO_TAXABLE_INCOME;
    }
    return taxableIncome;
  }

  /**
   * Method to calculate taxable income after childcare expense exemption
   * @param taxableIncome taxableIncome
   * @return taxable Income
   */
  public double taxableIncomeAfterChildCareExpense(double taxableIncome){
    if (this.getLastYearEarning()<MAX_INCOME_FOR_CHILDCARE_EXPENSE && this.childcareExpense>CHILDCARE_EXPENSE_MIN){
      taxableIncome = taxableIncome - CHILDCARE_EXPENSE_EXEMPTION;
    }
    return taxableIncome;
  }

  /**
   * Calculate tax amount after necessary deduction
   * @param taxableIncome taxableIncome
   * @return taxAmount
   */
  public double calculateTaxAmount(double taxableIncome){
    double taxAmount = INITIAL_TAX_AMOUNT;
    if (taxableIncome<TAXABLE_INCOME_LOWER_RANGE){
      taxAmount = taxableIncome*TAXABLE_INCOME_LOWER_RANGE_RATIO;
    }
    else{
      taxAmount = taxableIncome*TAXABLE_INCOME_UPPER_RANGE_RATIO;
    }
    return taxAmount;

  }

  /**
   * Method to calculate tax which extends the functionality calculated in parent class method
   * @return taxAmount
   */
  @Override
  public double calculateTax() {

    double taxableIncome = super.calculateTax();
    double healthAndRetirement = (this.getHealthSavingAccount()+this.getRetirementSavingAccount())*HEALTH_AND_RETIREMENT_CAL_RATIO;

    taxableIncome = (taxableIncome - calculateHealthAndRetirementExemption(healthAndRetirement));
    taxableIncome = setTaxableIncomeZero(taxableIncome);
    taxableIncome = taxableIncomeAfterChildCareExpense(taxableIncome);
    taxableIncome = setTaxableIncomeZero(taxableIncome);

    return calculateTaxAmount(taxableIncome);


  }

  /**
   * Method to get number of dependents
   * @return numberOfDependents
   */
  public int getNumberOfDependents() {
    return numberOfDependents;
  }

  /**
   * Method to set number of dependents
   * @param numberOfDependents numberOfDependents
   */
  public void setNumberOfDependents(int numberOfDependents) {
    this.numberOfDependents = numberOfDependents;
  }

  /**
   * Method to get number of minor children
   * @return numberOfMinorChildren
   */
  public int getNumberOfMinorChildren() {
    return numberOfMinorChildren;
  }

  /**
   * Method to set number of minor children
   * @param numberOfMinorChildren numberOfMinorChildren
   */
  public void setNumberOfMinorChildren(int numberOfMinorChildren) {
    this.numberOfMinorChildren = numberOfMinorChildren;
  }

  /**
   * Method to get children care expense
   * @return childcareExpense
   */
  public double getChildcareExpense() {
    return childcareExpense;
  }

  /**
   * Method to set children care expense
   * @param childcareExpense childcareExpense
   */
  public void setChildcareExpense(double childcareExpense) {
    this.childcareExpense = childcareExpense;
  }

  /**
   * Method to get dependent care expense
   * @return dependentCareExpense
   */
  public double getDependentCareExpense() {
    return dependentCareExpense;
  }

  /**
   * Method to set dependent care expense
   * @param dependentCareExpense dependentCareExpense
   */
  public void setDependentCareExpense(double dependentCareExpense) {
    this.dependentCareExpense = dependentCareExpense;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    GroupFiler that = (GroupFiler) o;
    return numberOfDependents == that.numberOfDependents
        && numberOfMinorChildren == that.numberOfMinorChildren
        && Double.compare(that.childcareExpense, childcareExpense) == 0
        && Double.compare(that.dependentCareExpense, dependentCareExpense) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), numberOfDependents, numberOfMinorChildren,
        childcareExpense,
        dependentCareExpense);
  }
}
