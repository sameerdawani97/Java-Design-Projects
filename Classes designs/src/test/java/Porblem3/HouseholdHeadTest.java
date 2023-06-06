package Porblem3;

import static org.junit.jupiter.api.Assertions.*;

import Problem3.ContactInfo;
import Problem3.Employee;
import Problem3.HouseholdHead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HouseholdHeadTest {
  ContactInfo contactInfo;
  HouseholdHead householdHead;

  @BeforeEach
  void setUp() {
    householdHead = new HouseholdHead();
    contactInfo = new ContactInfo();
  }

  @Test
  void testTaxId() {
    householdHead.setTaxId("123A");
    assertEquals("123A",householdHead.getTaxId());
  }

  @Test
  void testContactInfo() {

    householdHead.setContactInfo(contactInfo);
    assertEquals(contactInfo,householdHead.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    householdHead.setLastYearEarning(100000.0);
    assertEquals(100000.0,householdHead.getLastYearEarning());
  }

  @Test
  void testTaxAlreadyPaid() {
    householdHead.setTaxAlreadyPaid(5000.0);
    assertEquals(5000.0,householdHead.getTaxAlreadyPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    householdHead.setMortgageInterestPaid(5000.0);
    assertEquals(5000.0,householdHead.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    householdHead.setPropertyTaxesPaid(5000.0);
    assertEquals(5000.0,householdHead.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanTuitionPaid() {
    householdHead.setStudentLoanTuitionPaid(5000.0);
    assertEquals(5000.0,householdHead.getStudentLoanTuitionPaid());
  }

  @Test
  void testRetirementSavingAccount() {
    householdHead.setRetirementSavingAccount(5000.0);
    assertEquals(5000.0,householdHead.getRetirementSavingAccount());
  }

  @Test
  void testHealthSavingAccount() {
    householdHead.setHealthSavingAccount(5000.0);
    assertEquals(5000.0,householdHead.getHealthSavingAccount());
  }

  @Test
  void testDonationContribution() {
    householdHead.setDonationContribution(2000.0);
    assertEquals(2000.0,householdHead.getDonationContribution());
  }

  @Test
  void testNumberOfDependents() {
    householdHead.setNumberOfDependents(3);
    assertEquals(3,householdHead.getNumberOfDependents());
  }

  @Test
  void testNumberOfMinorChildren() {
    householdHead.setNumberOfMinorChildren(2);
    assertEquals(2,householdHead.getNumberOfMinorChildren());
  }

  @Test
  void testChildCareExpense() {
    householdHead.setChildcareExpense(6000);
    assertEquals(6000,householdHead.getChildcareExpense());
  }

  @Test
  void testDependentCareExpense() {
    householdHead.setDependentCareExpense(10000);
    assertEquals(10000,householdHead.getDependentCareExpense());
  }

  @Test
  void testHashCode() {
    assertEquals(super.hashCode(),super.hashCode());
    assertNotEquals(super.hashCode(),householdHead.hashCode());
    assertEquals(householdHead.hashCode(),householdHead.hashCode());
  }

  @Test
  void testEquals() {


    assertTrue(householdHead.equals(householdHead));
    HouseholdHead householdHead1 = new HouseholdHead();
    assertFalse(householdHead.equals(contactInfo));

    assertFalse(householdHead.equals(null));
    householdHead.setDependentCareExpense(10000);
    assertTrue(householdHead.equals(householdHead));
    assertFalse(householdHead.equals(new HouseholdHead()));
    assertFalse(householdHead.equals(householdHead1));
    assertTrue(householdHead.getClass().equals(householdHead1.getClass()));
    assertFalse(super.getClass().equals(householdHead.getClass()));


  }

  @Test
  void testCalculateTax() {
    householdHead.setTaxId("123A");
    householdHead.setLastYearEarning(10000.0);
    householdHead.setTaxAlreadyPaid(1000.0);
    householdHead.setMortgageInterestPaid(6000.0);
    householdHead.setPropertyTaxesPaid(8000.0);
    householdHead.setStudentLoanTuitionPaid(1000.0);
    householdHead.setRetirementSavingAccount(20000.0);
    householdHead.setHealthSavingAccount(20000.0);
    householdHead.setDonationContribution(1000.0);
    householdHead.setDependentCareExpense(10000);
    householdHead.setChildcareExpense(6000);

    HouseholdHead householdHead1 = new HouseholdHead();

    householdHead1.setTaxId("123B");
    householdHead1.setLastYearEarning(180000.0);
    householdHead1.setTaxAlreadyPaid(1000.0);
    householdHead1.setMortgageInterestPaid(6000.0);
    householdHead1.setPropertyTaxesPaid(8000.0);
    householdHead1.setStudentLoanTuitionPaid(1000.0);
    householdHead1.setRetirementSavingAccount(20000.0);
    householdHead1.setHealthSavingAccount(20000.0);
    householdHead1.setDonationContribution(1000.0);
    householdHead1.setDependentCareExpense(10000);
    householdHead1.setChildcareExpense(6000);

    assertEquals(0.0,householdHead.calculateTax());
    //assertEquals(8373.75,householdHead.calculateTax());
    assertNotEquals(20000.0,householdHead.calculateTax());

    assertEquals(29183.75,householdHead1.calculateTax());
    assertNotEquals(20000.0,householdHead1.calculateTax());

  }
}
