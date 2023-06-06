package Problem2;
import static org.junit.jupiter.api.Assertions.*;

import Problem2.PropulsionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoatTest {

  Boat boat;

  @BeforeEach
  void setUp() {
    boat = new Boat();

  }

  @Test
  void testBoat() {
    Boat boat1 = new Boat("123",2002,new MakeModel("Audi","A^"),
        20.0,10.F,10);

    boat.setBoatLength(10.F);
    assertNotEquals(boat,boat1);
    assertEquals(boat.getBoatLength(),boat1.getBoatLength());
  }

  @Test
  void testVehicleId() {
    boat.setVehicleId("123");
    assertEquals("123",boat.getVehicleId());
  }
  @Test
  void testManufacturingYear() {
    boat.setManufacturingYear(2000);
    assertEquals(2000,boat.getManufacturingYear());
  }
  @Test
  void testMsrp() {
    boat.setMsrp(20.0);
    assertEquals(20.0,boat.getMsrp());
    assertNotEquals(10.0,boat.getMsrp());
  }

  @Test
  void testMakeModel() {
    MakeModel makeModel = new MakeModel("Toyota","Accord");
    boat.setMakeModel(makeModel);
    assertEquals(makeModel,boat.getMakeModel());
  }

  @Test
  void testNumberOfPassengers() {
    boat.setNumberOfPassengers(10);
    assertEquals(10,boat.getNumberOfPassengers());
    assertNotEquals(5,boat.getNumberOfPassengers());
  }

  @Test
  void testBoatLength() {
    boat.setBoatLength(10.5F);
    assertEquals(10.5F,boat.getBoatLength());
    assertNotEquals(5,boat.getBoatLength());
  }

  @Test
  void testPropulsionType() {
    boat.setPropulsionType(PropulsionType.SailPower);
    assertEquals(PropulsionType.SailPower,boat.getPropulsionType());
    assertNotEquals(PropulsionType.InboardEngine,boat.getPropulsionType());
  }

  @Test
  void testHashCode() {
    Boat boat1 = new Boat();
    boat1.setBoatLength(10.0F);
    assertEquals(boat.hashCode(),boat.hashCode());
    assertNotEquals(boat.hashCode(),boat1.hashCode());
  }

  @Test
  void testEquals() {
    Boat boat1 = new Boat("123",2002,new MakeModel("Audi","A^"),
        20.0,10.F,10);
    assertTrue(boat.equals(boat));
    assertFalse(boat.equals(null));
    boat.setManufacturingYear(2001);
    assertTrue(boat.equals(boat));
    assertFalse(boat.equals(new NewCar()));
    assertFalse(boat.equals(boat1));
    assertTrue(boat.getClass().equals(Boat.class));

  }


}
