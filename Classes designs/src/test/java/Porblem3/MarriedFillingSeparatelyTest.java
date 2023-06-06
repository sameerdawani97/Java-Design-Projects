package Porblem3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Problem3.ContactInfo;
import Problem3.MarriedFillingSeparately;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarriedFillingSeparatelyTest {

  ContactInfo contactInfo;
  MarriedFillingSeparately marriedFillingSeparately;

  @BeforeEach
  void setUp() {
    marriedFillingSeparately = new MarriedFillingSeparately();
    contactInfo = new ContactInfo();
  }

  @Test
  void testTaxId() {
    marriedFillingSeparately.setTaxId("123A");
    assertEquals("123A",marriedFillingSeparately.getTaxId());
  }

  @Test
  void testContactInfo() {

    marriedFillingSeparately.setContactInfo(contactInfo);
    assertEquals(contactInfo,marriedFillingSeparately.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    marriedFillingSeparately.setLastYearEarning(100000.0);
    assertEquals(100000.0,marriedFillingSeparately.getLastYearEarning());
  }

  @Test
  void testTaxAlreadyPaid() {
    marriedFillingSeparately.setTaxAlreadyPaid(5000.0);
    assertEquals(5000.0,marriedFillingSeparately.getTaxAlreadyPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    marriedFillingSeparately.setMortgageInterestPaid(5000.0);
    assertEquals(5000.0,marriedFillingSeparately.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    marriedFillingSeparately.setPropertyTaxesPaid(5000.0);
    assertEquals(5000.0,marriedFillingSeparately.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanTuitionPaid() {
    marriedFillingSeparately.setStudentLoanTuitionPaid(5000.0);
    assertEquals(5000.0,marriedFillingSeparately.getStudentLoanTuitionPaid());
  }

  @Test
  void testRetirementSavingAccount() {
    marriedFillingSeparately.setRetirementSavingAccount(5000.0);
    assertEquals(5000.0,marriedFillingSeparately.getRetirementSavingAccount());
  }

  @Test
  void testHealthSavingAccount() {
    marriedFillingSeparately.setHealthSavingAccount(5000.0);
    assertEquals(5000.0,marriedFillingSeparately.getHealthSavingAccount());
  }

  @Test
  void testDonationContribution() {
    marriedFillingSeparately.setDonationContribution(2000.0);
    assertEquals(2000.0,marriedFillingSeparately.getDonationContribution());
  }

  @Test
  void testNumberOfDependents() {
    marriedFillingSeparately.setNumberOfDependents(3);
    assertEquals(3,marriedFillingSeparately.getNumberOfDependents());
  }

  @Test
  void testNumberOfMinorChildren() {
    marriedFillingSeparately.setNumberOfMinorChildren(2);
    assertEquals(2,marriedFillingSeparately.getNumberOfMinorChildren());
  }

  @Test
  void testChildCareExpense() {
    marriedFillingSeparately.setChildcareExpense(6000);
    assertEquals(6000,marriedFillingSeparately.getChildcareExpense());
  }

  @Test
  void testDependentCareExpense() {
    marriedFillingSeparately.setDependentCareExpense(10000);
    assertEquals(10000,marriedFillingSeparately.getDependentCareExpense());
  }

  @Test
  void testHashCode() {
    assertEquals(super.hashCode(),super.hashCode());
    assertNotEquals(super.hashCode(),marriedFillingSeparately.hashCode());
    assertEquals(marriedFillingSeparately.hashCode(),marriedFillingSeparately.hashCode());
  }

  @Test
  void testEquals() {


    assertTrue(marriedFillingSeparately.equals(marriedFillingSeparately));
    MarriedFillingSeparately marriedFillingSeparately1 = new MarriedFillingSeparately();
    assertFalse(marriedFillingSeparately.equals(contactInfo));

    assertFalse(marriedFillingSeparately.equals(null));
    marriedFillingSeparately.setDependentCareExpense(10000);
    assertTrue(marriedFillingSeparately.equals(marriedFillingSeparately));
    assertFalse(marriedFillingSeparately.equals(new MarriedFillingSeparately()));
    assertFalse(marriedFillingSeparately.equals(marriedFillingSeparately1));
    assertTrue(marriedFillingSeparately.getClass().equals(marriedFillingSeparately1.getClass()));
    assertFalse(super.getClass().equals(marriedFillingSeparately.getClass()));


  }

  @Test
  void testCalculateTax() {
    marriedFillingSeparately.setTaxId("123A");
    marriedFillingSeparately.setLastYearEarning(10000.0);
    marriedFillingSeparately.setTaxAlreadyPaid(1000.0);
    marriedFillingSeparately.setMortgageInterestPaid(6000.0);
    marriedFillingSeparately.setPropertyTaxesPaid(8000.0);
    marriedFillingSeparately.setStudentLoanTuitionPaid(1000.0);
    marriedFillingSeparately.setRetirementSavingAccount(20000.0);
    marriedFillingSeparately.setHealthSavingAccount(20000.0);
    marriedFillingSeparately.setDonationContribution(1000.0);
    marriedFillingSeparately.setDependentCareExpense(10000);
    marriedFillingSeparately.setChildcareExpense(6000);

    MarriedFillingSeparately marriedFillingSeparately1 = new MarriedFillingSeparately();

    marriedFillingSeparately1.setTaxId("123B");
    marriedFillingSeparately1.setLastYearEarning(180000.0);
    marriedFillingSeparately1.setTaxAlreadyPaid(1000.0);
    marriedFillingSeparately1.setMortgageInterestPaid(6000.0);
    marriedFillingSeparately1.setPropertyTaxesPaid(8000.0);
    marriedFillingSeparately1.setStudentLoanTuitionPaid(1000.0);
    marriedFillingSeparately1.setRetirementSavingAccount(20000.0);
    marriedFillingSeparately1.setHealthSavingAccount(20000.0);
    marriedFillingSeparately1.setDonationContribution(1000.0);
    marriedFillingSeparately1.setDependentCareExpense(10000);
    marriedFillingSeparately1.setChildcareExpense(6000);

    assertEquals(0.0,marriedFillingSeparately.calculateTax());
    //assertEquals(8373.75,householdHead.calculateTax());
    assertNotEquals(20000.0,marriedFillingSeparately.calculateTax());

    assertEquals(29183.75,marriedFillingSeparately1.calculateTax());
    assertNotEquals(20000.0,marriedFillingSeparately1.calculateTax());

  }
}
