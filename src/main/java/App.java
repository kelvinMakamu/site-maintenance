import components.engineers.Engineer;
import components.sites.Site;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getAssignedServerPort(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        return processBuilder.environment().get("PORT") != null
                ? Integer.parseInt(processBuilder.environment().get("PORT"))
                : 4500;
    }

    public static void main(String[] args) {
        port(getAssignedServerPort());
        staticFileLocation("/public");

        get("/",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            model.put("engineers",engineers);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/engineers",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            List<Engineer> engineers = Engineer.all();
            model.put("engineers",engineers);
            return new ModelAndView(model,"engineer.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sites",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            List<Site> sites = Site.all();
            model.put("sites",sites);
            return new ModelAndView(model,"site.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
