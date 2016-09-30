import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void getLocation_instantiatesCorrectlyWithLocation_string() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    assertEquals("Mountains", testSighting.getLocation());
  }

  @Test
  public void getRangerName_instantiatesCorrectlyWithRangerName_string() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    assertEquals("George", testSighting.getRangerName());
  }

  @Test
  public void getAnimalId_instantiatesCorrectlyWithAnimalId_int() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    assertEquals(1, testSighting.getAnimalId());
  }

  @Test
  public void equals_returnsTrueArgumentsAreSame_True() {
    Sighting firstSighting = new Sighting(1, "Mountains", "George");
    Sighting secondSighting = new Sighting(1, "Mountains", "George");
    assertTrue(firstSighting.equals(secondSighting));
  }

  @Test
  public void save_returnsTrueIfObjectsAreTheSame() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    testSighting.save();
    assertTrue(Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void save_assignsIdToObject_true() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    testSighting.save();
    Sighting savedSighting = Sighting.all().get(0);
    assertEquals(savedSighting.getId(), testSighting.getId());
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    Sighting firstSighting = new Sighting(1, "Mountains", "George");
    firstSighting.save();
    Sighting secondSighting = new Sighting(2, "Grasslands", "Rick");
    secondSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(firstSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    Sighting firstSighting = new Sighting(1, "Mountains", "George");
    firstSighting.save();
    Sighting secondSighting = new Sighting(2, "Grasslands", "Rick");
    secondSighting.save();
    assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
  }

  @Test
  public void save_savesSightingWithAnimalIdIntoDB_true() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    testEndangered.save();
    Sighting testSighting = new Sighting(testEndangered.getId(), "Mountains", "George");
    testSighting.save();
    Sighting savedSighting = Sighting.find(testSighting.getId());
    assertEquals(savedSighting.getAnimalId(), testEndangered.getId());
  }

  @Test
  public void save_savesSightingWithAnimalIdIntoDBu_true() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    testUnendangered.save();
    Sighting testSighting = new Sighting(testUnendangered.getId(), "Mountains", "George");
    testSighting.save();
    Sighting savedSighting = Sighting.find(testSighting.getId());
    assertEquals(savedSighting.getAnimalId(), testUnendangered.getId());
  }

  @Test
  public void save_recordsTimeOfCreationInDatabase() {
    Sighting testSighting = new Sighting(1, "Mountains", "George");
    testSighting.save();
    Timestamp savedSighting = Sighting.find(testSighting.getId()).getSightingTime();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getDay(), savedSighting.getDay());
  }
}
