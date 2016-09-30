import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EndangeredTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangered_instantiatesCorrectly_true() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    assertEquals(true, testEndangered instanceof Endangered);
  }

  @Test
  public void getHealth_instantiatesCorrectlyWithHealth_true() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    assertEquals("Healthy", testEndangered.getHealth());
  }

  @Test
  public void getAge_instantiatesCorrectlyWithAge_true() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    assertEquals("Newborn", testEndangered.getAge());
  }

  @Test
  public void equals_returnsTrueIfNameAndAgeAreSame_True() {
    Endangered firstEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    Endangered secondEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    assertTrue(firstEndangered.equals(secondEndangered));
  }

  @Test
  public void save_returnsTrueIfObjectsAreTheSame() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    testEndangered.save();
    assertTrue(Endangered.all().get(0).equals(testEndangered));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    testEndangered.save();
    Endangered savedEndangered = Endangered.all().get(0);
    assertEquals(savedEndangered.getId(), testEndangered.getId());
  }

  @Test
  public void all_returnsAllInstancesOfEndangered_true() {
    Endangered firstEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    firstEndangered.save();
    Endangered secondEndangered = new Endangered("Elephant", "Ill", "Adult");
    secondEndangered.save();
    assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
    assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
  }

  @Test
  public void find_returnsEndangeredWithSameId_secondEndangered() {
    Endangered firstEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    firstEndangered.save();
    Endangered secondEndangered = new Endangered("Elephant", "Ill", "Adult");
    secondEndangered.save();
    assertEquals(Endangered.find(secondEndangered.getId()), secondEndangered);
  }

  @Test
  public void getSightings_retrievesAllSightingsFromDB_List() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    testEndangered.save();
    Sighting firstSighting = new Sighting(testEndangered.getId(), "Mountains", "Greg");
    firstSighting.save();
    Sighting secondSighting = new Sighting(testEndangered.getId(), "Mountains", "George");
    secondSighting.save();
    Object[] sightings = new Object[] { firstSighting, secondSighting };
    assertTrue(testEndangered.getSightings().containsAll(Arrays.asList(sightings)));
  }



}
