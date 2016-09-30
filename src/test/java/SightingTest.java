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

  // @Test
  // public void save_savesFireMonsterWithPersonIdIntoDB_true() {
  //   Person testPerson = new Person("Henry", "henry@henry.com");
  //   testPerson.save();
  //   FireMonster testFireMonster = new FireMonster("Bubbles", testPerson.getId());
  //   testFireMonster.save();
  //   FireMonster savedFireMonster = FireMonster.find(testFireMonster.getId());
  //   assertEquals(savedFireMonster.getPersonId(), testPerson.getId());
  // }
  //
  // @Test
  // public void save_recordsTimeOfCreationInDatabase() {
  //   FireMonster testFireMonster = new FireMonster("Bubbles", 1);
  //   testFireMonster.save();
  //   Timestamp savedFireMonsterBirthday = FireMonster.find(testFireMonster.getId()).getBirthday();
  //   Timestamp rightNow = new Timestamp(new Date().getTime());
  //   assertEquals(rightNow.getDay(), savedFireMonsterBirthday.getDay());
  // }
}
