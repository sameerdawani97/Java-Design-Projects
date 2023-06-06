package Porblem3;
import static org.junit.jupiter.api.Assertions.*;

import Problem2.Boat;
import Problem2.MakeModel;
import Problem2.NewCar;
import Problem3.ContactInfo;
import Problem3.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeTest {

  Employee employee;
  ContactInfo contactInfo;

  @BeforeEach
  void setUp() {
    employee = new Employee();
    contactInfo = new ContactInfo();
  }

  @Test
  void testTaxId() {
    employee.setTaxId("123A");
    assertEquals("123A",employee.getTaxId());
  }

  @Test
  void testContactInfo() {

    employee.setContactInfo(contactInfo);
    assertEquals(contactInfo,employee.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    employee.setLastYearEarning(100000.0);
    assertEquals(100000.0,employee.getLastYearEarning());
  }

  @Test
  void testTaxAlreadyPaid() {
    employee.setTaxAlreadyPaid(5000.0);
    assertEquals(5000.0,employee.getTaxAlreadyPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    employee.setMortgageInterestPaid(5000.0);
    assertEquals(5000.0,employee.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    employee.setPropertyTaxesPaid(5000.0);
    assertEquals(5000.0,employee.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanTuitionPaid() {
    employee.setStudentLoanTuitionPaid(5000.0);
    assertEquals(5000.0,employee.getStudentLoanTuitionPaid());
  }

  @Test
  void testRetirementSavingAccount() {
    employee.setRetirementSavingAccount(5000.0);
    assertEquals(5000.0,employee.getRetirementSavingAccount());
  }

  @Test
  void testHealthSavingAccount() {
    employee.setHealthSavingAccount(5000.0);
    assertEquals(5000.0,employee.getHealthSavingAccount());
  }

  @Test
  void testDonationContribution() {
    employee.setDonationContribution(2000.0);
    assertEquals(2000.0,employee.getDonationContribution());
  }

  @Test
  void testCalculateTax() {
    employee.setTaxId("123A");
    employee.setLastYearEarning(100000.0);
    employee.setTaxAlreadyPaid(1000.0);
    employee.setMortgageInterestPaid(1000.0);
    employee.setPropertyTaxesPaid(1000.0);
    employee.setStudentLoanTuitionPaid(1000.0);
    employee.setRetirementSavingAccount(1000.0);
    employee.setHealthSavingAccount(1000.0);
    employee.setDonationContribution(1000.0);

    assertEquals(18544.0,employee.calculateTax());
    assertNotEquals(20000.0,employee.calculateTax());

  }

  @Test
  void testCalculateTaxMethodBranch() {
    employee.setTaxId("123A");
    employee.setLastYearEarning(10000.0);
    employee.setTaxAlreadyPaid(1000.0);
    employee.setMortgageInterestPaid(6000.0);
    employee.setPropertyTaxesPaid(8000.0);
    employee.setStudentLoanTuitionPaid(1000.0);
    employee.setRetirementSavingAccount(20000.0);
    employee.setHealthSavingAccount(20000.0);
    employee.setDonationContribution(1000.0);

    assertEquals(0.0,employee.calculateTax());
    //assertEquals(7275.0,employee.calculateTax());
    assertNotEquals(20000.0,employee.calculateTax());

  }

  @Test
  void testHashCode() {
    assertEquals(employee.hashCode(),employee.hashCode());
  }

  @Test
  void testEquals() {
    assertTrue(employee.equals(employee));
    Employee employee1 = new Employee();
    assertFalse(employee.equals(null));
    employee.setLastYearEarning(200000.0);
    assertTrue(employee.equals(employee));
    assertFalse(employee.equals(new Employee()));
    assertFalse(employee.equals(employee1));
    assertTrue(employee.getClass().equals(employee1.getClass()));
    assertFalse(super.getClass().equals(employee.getClass()));

  }







}
