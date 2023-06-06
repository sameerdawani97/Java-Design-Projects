package Porblem3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Problem3.ContactInfo;
import Problem3.MarriedFillingJointly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarriedFillingJointlyTest {

  ContactInfo contactInfo;
  MarriedFillingJointly marriedFillingJointly;

  @BeforeEach
  void setUp() {
    marriedFillingJointly = new MarriedFillingJointly();
    contactInfo = new ContactInfo();
  }

  @Test
  void testTaxId() {
    marriedFillingJointly.setTaxId("123A");
    assertEquals("123A",marriedFillingJointly.getTaxId());
  }

  @Test
  void testContactInfo() {

    marriedFillingJointly.setContactInfo(contactInfo);
    assertEquals(contactInfo,marriedFillingJointly.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    marriedFillingJointly.setLastYearEarning(100000.0);
    assertEquals(100000.0,marriedFillingJointly.getLastYearEarning());
  }

  @Test
  void testTaxAlreadyPaid() {
    marriedFillingJointly.setTaxAlreadyPaid(5000.0);
    assertEquals(5000.0,marriedFillingJointly.getTaxAlreadyPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    marriedFillingJointly.setMortgageInterestPaid(5000.0);
    assertEquals(5000.0,marriedFillingJointly.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    marriedFillingJointly.setPropertyTaxesPaid(5000.0);
    assertEquals(5000.0,marriedFillingJointly.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanTuitionPaid() {
    marriedFillingJointly.setStudentLoanTuitionPaid(5000.0);
    assertEquals(5000.0,marriedFillingJointly.getStudentLoanTuitionPaid());
  }

  @Test
  void testRetirementSavingAccount() {
    marriedFillingJointly.setRetirementSavingAccount(5000.0);
    assertEquals(5000.0,marriedFillingJointly.getRetirementSavingAccount());
  }

  @Test
  void testHealthSavingAccount() {
    marriedFillingJointly.setHealthSavingAccount(5000.0);
    assertEquals(5000.0,marriedFillingJointly.getHealthSavingAccount());
  }

  @Test
  void testDonationContribution() {
    marriedFillingJointly.setDonationContribution(2000.0);
    assertEquals(2000.0,marriedFillingJointly.getDonationContribution());
  }

  @Test
  void testNumberOfDependents() {
    marriedFillingJointly.setNumberOfDependents(3);
    assertEquals(3,marriedFillingJointly.getNumberOfDependents());
  }

  @Test
  void testNumberOfMinorChildren() {
    marriedFillingJointly.setNumberOfMinorChildren(2);
    assertEquals(2,marriedFillingJointly.getNumberOfMinorChildren());
  }

  @Test
  void testChildCareExpense() {
    marriedFillingJointly.setChildcareExpense(6000);
    assertEquals(6000,marriedFillingJointly.getChildcareExpense());
  }

  @Test
  void testDependentCareExpense() {
    marriedFillingJointly.setDependentCareExpense(10000);
    assertEquals(10000,marriedFillingJointly.getDependentCareExpense());
  }

  @Test
  void testHashCode() {
    assertEquals(super.hashCode(),super.hashCode());
    assertNotEquals(super.hashCode(),marriedFillingJointly.hashCode());
    assertEquals(marriedFillingJointly.hashCode(),marriedFillingJointly.hashCode());
  }

  @Test
  void testEquals() {


    assertTrue(marriedFillingJointly.equals(marriedFillingJointly));
    MarriedFillingJointly marriedFillingJointly1 = new MarriedFillingJointly();
    assertFalse(marriedFillingJointly.equals(contactInfo));

    assertFalse(marriedFillingJointly.equals(null));
    marriedFillingJointly.setDependentCareExpense(10000);
    assertTrue(marriedFillingJointly.equals(marriedFillingJointly));
    assertFalse(marriedFillingJointly.equals(new MarriedFillingJointly()));
    assertFalse(marriedFillingJointly.equals(marriedFillingJointly1));
    assertTrue(marriedFillingJointly.getClass().equals(marriedFillingJointly1.getClass()));
    assertFalse(super.getClass().equals(marriedFillingJointly.getClass()));


  }

  @Test
  void testCalculateTax() {
    marriedFillingJointly.setTaxId("123A");
    marriedFillingJointly.setLastYearEarning(10000.0);
    marriedFillingJointly.setTaxAlreadyPaid(1000.0);
    marriedFillingJointly.setMortgageInterestPaid(6000.0);
    marriedFillingJointly.setPropertyTaxesPaid(8000.0);
    marriedFillingJointly.setStudentLoanTuitionPaid(1000.0);
    marriedFillingJointly.setRetirementSavingAccount(20000.0);
    marriedFillingJointly.setHealthSavingAccount(20000.0);
    marriedFillingJointly.setDonationContribution(1000.0);
    marriedFillingJointly.setDependentCareExpense(10000);
    marriedFillingJointly.setChildcareExpense(6000);

    MarriedFillingJointly marriedFillingJointly1 = new MarriedFillingJointly();

    marriedFillingJointly1.setTaxId("123B");
    marriedFillingJointly1.setLastYearEarning(180000.0);
    marriedFillingJointly1.setTaxAlreadyPaid(1000.0);
    marriedFillingJointly1.setMortgageInterestPaid(6000.0);
    marriedFillingJointly1.setPropertyTaxesPaid(8000.0);
    marriedFillingJointly1.setStudentLoanTuitionPaid(1000.0);
    marriedFillingJointly1.setRetirementSavingAccount(20000.0);
    marriedFillingJointly1.setHealthSavingAccount(20000.0);
    marriedFillingJointly1.setDonationContribution(1000.0);
    marriedFillingJointly1.setDependentCareExpense(10000);
    marriedFillingJointly1.setChildcareExpense(6000);

    assertEquals(0.0,marriedFillingJointly.calculateTax());
    //assertEquals(8373.75,householdHead.calculateTax());
    assertNotEquals(20000.0,marriedFillingJointly.calculateTax());

    assertEquals(29183.75,marriedFillingJointly1.calculateTax());
    assertNotEquals(20000.0,marriedFillingJointly1.calculateTax());

  }
}
