package Problem1;
import static org.junit.jupiter.api.Assertions.*;

import Problem1.exceptions.customException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlyerTest {
  Flyer flyer;
  Name name;
  MilesBalance milesBalance;
  FlyerStore flyerStore;
  Deposit deposit;
  @BeforeEach
  void setUp() {
    flyer = new Flyer();
    name = new Name("sam","","daw");
    milesBalance = new MilesBalance(2000,1500,1500);
    flyerStore = new FlyerStore();
    deposit = new Deposit(1500,"123456789012",name);

  }

  @Test
  void testFlyerAccountIdLength() {
    try{
      Flyer flyer1 = flyerStore.createFlyer("1234","sam@gmail.com",milesBalance,name,flyerStore);
      fail("AccountId length should be 12");
    }
    catch (customException e){
      assertEquals("AccountId length should be 12..!",e.getMessage());
      assertNotEquals(null,e.getMessage());

    }
  }

  @Test
  void testFlyerAccountExistence() {
    try{
      Flyer flyer1 = flyerStore.createFlyer("123456789012","sam@gmail.com",milesBalance,name,flyerStore);
      Flyer flyer2 = flyerStore.createFlyer("123456789012","sameer@gmail.com",milesBalance,name,flyerStore);
      fail("Exception should be thrown due to same accountId..!");

    }
    catch(customException e){
      assertEquals("Account already exists..!",e.getMessage());
      assertNotEquals(null,e.getMessage());
    }
  }

  @Test
  void testFlyerAccountId() {
    flyer.setAccountId("12345");
    assertEquals("12345",flyer.getAccountId());
    assertNotEquals("1234",flyer.getAccountId());
  }

  @Test
  void testFlyerEmail() {
    flyer.setEmailId("sam@gmail.com");
    assertEquals("sam@gmail.com",flyer.getEmailId());
    assertNotEquals("1234",flyer.getEmailId());
  }

  @Test
  void testFlyerMileBalance() {
    flyer.setMilesBalance(milesBalance);
    assertEquals(milesBalance,flyer.getMilesBalance());
    assertNotEquals(new MilesBalance(100,200,300),flyer.getMilesBalance());
  }

  @Test
  void testFlyerName() {
    flyer.setName(name);
    assertEquals(name,flyer.getName());
    assertNotEquals(new Name("daw","sd","sam"),flyer.getName());
  }

  @Test
  void testFlyerStore() {
    flyer.setFlyerStore(flyerStore);
    assertEquals(flyerStore,flyer.getFlyerStore());
  }

  @Test
  void testValidateAmount() {
    flyer.setMilesBalance(milesBalance);
    assertTrue(flyer.validateAmount(deposit));
    milesBalance.setTotalMiles(1000);
    flyer.setMilesBalance(milesBalance);
    assertFalse(flyer.validateAmount(deposit));
  }

  @Test
  void testPrivacyCheck() {
    Flyer flyer1 = flyerStore.createFlyer("123456789012","sam@gmail.com",milesBalance,name,flyerStore);
    Flyer flyer2 = flyerStore.createFlyer("123456789013","sameer@gmail.com",milesBalance,name,flyerStore);
    assertFalse(flyer1.privacyCheck("123456789015",name));
    assertTrue(flyer1.privacyCheck("123456789013",name));
  }

  @Test
  void testTransferMilesRecipientIncorrect() {
    Flyer flyer2 = flyerStore.createFlyer("123456789013","sameer@gmail.com",milesBalance,name,flyerStore);
    Flyer flyer3 = flyerStore.createFlyer("123456789014","sameer@gmail.com",milesBalance,name,flyerStore);
    try{
      flyer2.transferMiles(deposit);
      fail("Recipient details are incorrect exception..!");
    }
    catch(customException e){
      if (e.getMessage()=="Recipient details are incorrect..!"){
        assertEquals("Recipient details are incorrect..!",e.getMessage());

      }

    }
  }

  @Test
  void testTransferMilesAmountMoreThanAvailable() {
    Flyer flyer2 = flyerStore.createFlyer("123456789013","sameer@gmail.com",milesBalance,name,flyerStore);
    Flyer flyer3 = flyerStore.createFlyer("123456789014","sameer@gmail.com",milesBalance,name,flyerStore);
    Deposit deposit1 = new Deposit(2500,"123456789014",name);
    try{
      flyer2.transferMiles(deposit1);
      fail("transfer miles are more than balance available exception..!");
    }
    catch(customException e){
      if(e.getMessage()=="Deposit miles are greater than miles available..!"){
        assertEquals("Deposit miles are greater than miles available..!",e.getMessage());
      }

    }
  }

  @Test
  void testTransferMiles() {
    Flyer flyer2 = flyerStore.createFlyer("123456789012","sameer@gmail.com", milesBalance,name,flyerStore);
    Flyer flyer3 = flyerStore.createFlyer("123456789013","sameer@gmail.com",
        new MilesBalance(2500,1000,1000),
        new Name("sameer","","daw"),flyerStore);

    Deposit deposit1 = new Deposit(1500, "123456789013", new Name("sameer", "", "daw"));
    flyer2.transferMiles(deposit1);
    assertEquals(4000, flyer3.getMilesBalance().getTotalMiles());
    assertEquals(2500, flyer3.getMilesBalance().getExpiringMiles());
    assertEquals(2500, flyer3.getMilesBalance().getEarnedMiles());
    assertEquals(500, flyer2.getMilesBalance().getTotalMiles());
    assertEquals(0, flyer2.getMilesBalance().getExpiringMiles());
    assertEquals(0, flyer2.getMilesBalance().getEarnedMiles());

  }


  @Test
  void testHashCode() {
    Flyer flyer1 = flyerStore.createFlyer("123456789012","sam@gmail.com",milesBalance,name,flyerStore);
    Flyer flyer2 = flyerStore.createFlyer("123456789013","sameer@gmail.com",milesBalance,name,flyerStore);
    assertEquals(flyer1.hashCode(),flyer1.hashCode());
    assertNotEquals(flyer1.hashCode(),flyer2.hashCode());
  }

  @Test
  void testEquals(){
    Flyer flyer1 = flyerStore.createFlyer("123456789012","sam@gmail.com",milesBalance,name,flyerStore);
    Flyer flyer2 = flyerStore.createFlyer("123456789013","sameer@gmail.com",milesBalance,name,flyerStore);
    Flyer flyer3 = new Flyer();

    assertTrue(flyer.getClass().equals(Flyer.class));
    assertFalse(flyer1.equals(null));
    assertTrue(flyer1.equals(flyer1));
    assertTrue(flyer2.equals(flyer2));
    assertFalse(flyer1.equals(flyer2));
    assertFalse(flyer1.equals(flyer3));
    assertFalse(flyer3.equals(null));

  }



}




