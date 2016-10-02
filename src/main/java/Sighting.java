import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class Sighting {
  private int id;
  private int animalId;
  private String location;
  private String rangerName;
  private Timestamp sightingTime;


  public Sighting(int animalId, String location, String rangerName) {
    this.animalId = animalId;
    this.location = location;
    this.rangerName = rangerName;
  }

  public String getLocation() {
    return location;
  }

  public String getRangerName() {
    return rangerName;
  }

  public int getAnimalId() {
    return animalId;
  }

  public Timestamp getSightingTime() {
    return sightingTime;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherSighting) {
    if(!(otherSighting instanceof Sighting)) {
      return false;
    } else {
      Sighting newSighting = (Sighting) otherSighting;
      return this.getLocation().equals(newSighting.getLocation()) &&
      this.getRangerName().equals(newSighting.getRangerName()) &&
      this.getId() == newSighting.getId() &&
      this.getAnimalId() == newSighting.getAnimalId();
    }
  }

  public static List<Sighting> all() {
    String sql = "SELECT * FROM sightings";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Sighting.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      if(this.getLocation().equals("") || this.getRangerName().equals(""))  {
        throw new IllegalArgumentException("You must fill in all fields!");
      }
      String sql = "INSERT INTO sightings (animalId, location, rangerName, sightingTime) VALUES (:animalId, :location, :rangerName, now())";
      this.id = (int) con.createQuery(sql, true)
          .addParameter("animalId", this.animalId)
          .addParameter("location", this.location)
          .addParameter("rangerName", this.rangerName)
          .executeUpdate()
          .getKey();
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings where id=:id";
      Sighting sighting = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Sighting.class);
    return sighting;
    }
  }

}
