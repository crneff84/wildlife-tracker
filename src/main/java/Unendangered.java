import java.util.List;
import org.sql2o.*;

public class Unendangered extends Animal {
  public static final String DATABASE_TYPE = "unendangered";

  public Unendangered(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
    type = DATABASE_TYPE;
  }

  public static List<Unendangered> all() {
    String sql = "SELECT * FROM animals WHERE type = 'unendangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .throwOnMappingFailure(false)
      .executeAndFetch(Unendangered.class);
    }
  }

  public static Unendangered find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id = :id";
      Unendangered unendangered = con.createQuery(sql)
       .addParameter("id", id)
       .throwOnMappingFailure(false)
       .executeAndFetchFirst(Unendangered.class);
    return unendangered;
    }
  }

    public List<Unendangered> getSightings() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM sightings WHERE animalId = :id;";
          return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Unendangered.class);
        }
    }

}
