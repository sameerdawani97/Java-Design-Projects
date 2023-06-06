package Problem2;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NewCarTest {


  NewCar newCar;

  @BeforeEach
  void setUp() {

    newCar = new NewCar();
  }

  @Test
  void testNewCar() {
    NewCar newCar1 = new NewCar("123",2000,new MakeModel("Audi","A6"),
        20,40);

    assertNotEquals(newCar,newCar1);
  }

  @Test
  void testVehicleId() {
    newCar.setVehicleId("123");
    assertEquals("123",newCar.getVehicleId());
  }
  @Test
  void testManufacturingYear() {
    newCar.setManufacturingYear(2000);
    assertEquals(2000,newCar.getManufacturingYear());
  }
  @Test
  void testMsrp() {
    newCar.setMsrp(20.0);
    assertEquals(20.0,newCar.getMsrp());
    assertNotEquals(10.0,newCar.getMsrp());
  }

  @Test
  void testMakeModel() {
    MakeModel makeModel = new MakeModel("Toyota","Accord");
    newCar.setMakeModel(makeModel);
    assertEquals(makeModel,newCar.getMakeModel());
  }

  @Test
  void testCountCars50miles() {

    newCar.setCountCars50miles(20);
    assertEquals(20,newCar.getCountCars50miles());
    assertNotEquals(10,newCar.getCountCars50miles());
  }

  @Test
  void testHashCode() {
    NewCar newCar1 = new NewCar();
    newCar1.setManufacturingYear(2000);
    assertEquals(newCar.hashCode(),newCar.hashCode());
  }

  @Test
  void testEquals() {
    NewCar newCar1 = new NewCar();
    assertTrue(newCar.equals(newCar));
    assertFalse(newCar.equals(null));
    newCar.setManufacturingYear(2001);
    assertTrue(newCar.equals(newCar));
    assertTrue(newCar.equals(newCar1));
    assertTrue(newCar.getClass().equals(newCar1.getClass()));

  }


}
