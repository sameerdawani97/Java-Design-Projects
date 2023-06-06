package Problem3;

/**
 * Class for IndividualFiler which is inherited by TaxFiler
 */
public abstract class IndividualFiler extends TaxFiler{

  /**
   * Initial tax amount is fixed 0
   */
  public  static final double INITIAL_TAX_AMOUNT = 0;
  /**
   * health and retirement calculating is done by 0.7 of amount
   */
  public  static final double HEALTH_AND_RETIREMENT_CAL_RATIO = 0.7;
  /**
   * To represent zero taxable income
   */
  public  static final int ZERO_TAXABLE_INCOME = 0;

  /**
   * taxable income lower range is upto 55000
   */
  public  static final int TAXABLE_INCOME_LOWER_RANGE = 55000;
  /**
   * lower taxable income tax calculating by multiplying with this ratio
   */
  public  static final double TAXABLE_INCOME_LOWER_RANGE_RATIO = 0.15;

  /**
   * higher taxable income tax calculating by multiplying with this ratio
   */
  public  static final double TAXABLE_INCOME_UPPER_RANGE_RATIO = 0.19;
  /**
   * Default constructor
   */
  public IndividualFiler(){
    super();
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
    double taxAmount = INITIAL_TAX_AMOUNT;
    double taxableIncome = super.calculateTax();
    double healthAndRetirement = (this.getHealthSavingAccount()+this.getRetirementSavingAccount())*HEALTH_AND_RETIREMENT_CAL_RATIO;
    taxableIncome = (taxableIncome - healthAndRetirement);
    taxableIncome = setTaxableIncomeZero(taxableIncome);

    return calculateTaxAmount(taxableIncome);
  }


}
