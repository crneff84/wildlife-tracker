import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnendangeredTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void unendangered_instantiatesCorrectly_true() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    assertEquals(true, testUnendangered instanceof Unendangered);
  }

  @Test
  public void getHealth_instantiatesCorrectlyWithHealth_true() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    assertEquals("Healthy", testUnendangered.getHealth());
  }

  @Test
  public void getAge_instantiatesCorrectlyWithAge_true() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    assertEquals("Newborn", testUnendangered.getAge());
  }

  @Test
  public void equals_returnsTrueIfNameAndAgeAreSame_True() {
    Unendangered firstUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    Unendangered secondUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    assertTrue(firstUnendangered.equals(secondUnendangered));
  }

  @Test
  public void save_returnsTrueIfObjectsAreTheSame() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    testUnendangered.save();
    assertTrue(Unendangered.all().get(0).equals(testUnendangered));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    testUnendangered.save();
    Unendangered savedUnendangered = Unendangered.all().get(0);
    assertEquals(savedUnendangered.getId(), testUnendangered.getId());
  }

  @Test
  public void all_returnsAllInstancesOfUnendangered_true() {
    Unendangered firstUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    firstUnendangered.save();
    Unendangered secondUnendangered = new Unendangered("Elephant", "Ill", "Adult");
    secondUnendangered.save();
    assertEquals(true, Unendangered.all().get(0).equals(firstUnendangered));
    assertEquals(true, Unendangered.all().get(1).equals(secondUnendangered));
  }

  @Test
  public void find_returnsUnendangeredWithSameId_secondUnendangered() {
    Unendangered firstUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    firstUnendangered.save();
    Unendangered secondUnendangered = new Unendangered("Elephant", "Ill", "Adult");
    secondUnendangered.save();
    assertEquals(Unendangered.find(secondUnendangered.getId()), secondUnendangered);
  }

  // @Test
  // public void getSightings_retrievesAllSightingsFromDB_List() {
  //   Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
  //   testUnendangered.save();
  //   Sighting firstSighting = new Sighting(testUnendangered.getId(), "Mountains", "Greg");
  //   firstSighting.save();
  //   Sighting secondSighting = new Sighting(testUnendangered.getId(), "Mountains", "George");
  //   secondSighting.save();
  //   Object[] sightings = new Object[] { firstSighting, secondSighting };
  //   assertTrue(testUnendangered.getSightings().containsAll(Arrays.asList(sightings)));
  // }



}
