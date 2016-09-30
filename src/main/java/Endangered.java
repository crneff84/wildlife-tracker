import java.util.List;
import org.sql2o.*;

public class Endangered extends Animal {
  public static final String DATABASE_TYPE = "endangered";

  public Endangered(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
    type = DATABASE_TYPE;
  }

  public static List<Endangered> all() {
    String sql = "SELECT * FROM animals WHERE type = 'endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Endangered.class);
    }
  }

  public static Endangered find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id = :id";
      Endangered endangered = con.createQuery(sql)
       .addParameter("id", id)
       .throwOnMappingFailure(false)
       .executeAndFetchFirst(Endangered.class);
    return endangered;
    }
  }

  public List<Sighting> getSightings() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM sightings WHERE animalId = :id;";
        return con.createQuery(sql)
                  .addParameter("id", this.id)
                  .throwOnMappingFailure(false)
                  .executeAndFetch(Sighting.class);
      }
  }


}
