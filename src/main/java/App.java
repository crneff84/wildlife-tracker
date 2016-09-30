import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Arrays;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("endangereds", Endangered.all());
      model.put("unendangereds", Unendangered.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("endangereds", Endangered.all());
      model.put("template", "templates/endangereds.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      String name = request.queryParams("name");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      Endangered newEndangered = new Endangered(name, health, age);
      newEndangered.save();
      model.put("endangereds", Endangered.all());
      model.put("unendangereds", Unendangered.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/endangereds/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      Endangered endangered = Endangered.find(Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
      model.put("sighting", sighting);
      model.put("endangered", endangered);
      model.put("endangereds", Endangered.all());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/sightings", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("animalId");
      String name = request.queryParams("name");
      String name = request.queryParams("name");
      Endangered endangered = Endangered.find(Integer.parseInt(request.params(":id")));
      endangered.updateName(name);
      String url = String.format("/endangereds/%d", endangered.getId());
      response.redirect(url);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/unendangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("unendangereds", Endangered.all());
      model.put("template", "templates/unendangereds.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/unendangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      String name = request.queryParams("name");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      Unendangered newUnendangered = new Unendangered(name, health, age);
      newUnendangered.save();
      model.put("unendangereds", Unendangered.all());
      model.put("endangereds", Endangered.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

  }
}
