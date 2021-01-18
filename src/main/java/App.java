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
            model.put("createdEngineer",req.session().attribute("createdEngineer"));
            req.session().removeAttribute("createdEngineer");
            model.put("updatedEngineer",req.session().attribute("updatedEngineer"));
            req.session().removeAttribute("updatedEngineer");
            model.put("deletedEngineer",req.session().attribute("deletedEngineer"));
            req.session().removeAttribute("deletedEngineer");
            return new ModelAndView(model,"engineer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/engineers",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            String firstName  = req.queryParams("firstName");
            String lastName   = req.queryParams("lastName");
            Engineer engineer = new Engineer(firstName,lastName);
            engineer.save();
            req.session().attribute("createdEngineer","Engineer was added successfully!");
            res.redirect("/engineers");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/engineers/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int engineerId    = Integer.parseInt(req.params("id"));
            Engineer engineer = Engineer.find(engineerId);
            model.put("engineer",engineer);
            return new ModelAndView(model,"edit_engineer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/engineers/:id", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int engineerId    = Integer.parseInt(req.params("id"));
            String firstName  = req.queryParams("firstName");
            String lastName   = req.queryParams("lastName");
            Engineer engineer = Engineer.find(engineerId);
            engineer.update(firstName,lastName);
            req.session().attribute("updatedEngineer","Engineer was updated successfully!");
            res.redirect("/engineers");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/engineers/:id/view", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            Engineer engineer = Engineer.find(Integer.parseInt(req.params("id")));
            model.put("engineer",engineer);
            List<Site> foundSites = Site.getSitesNotAssigned();
            model.put("foundSites",foundSites);
            List<Site> sites  = engineer.getAssignedSites();
            model.put("sites",sites);
            model.put("createdEngineer",req.session().attribute("createdEngineer"));
            req.session().removeAttribute("createdEngineer");
            return new ModelAndView(model,"view_engineer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/engineers/:id/assignSite", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            int engineerId    = Integer.parseInt(req.params("id"));
            Engineer engineer = Engineer.find(engineerId);
            int siteId  = Integer.parseInt(req.queryParams("siteId"));
            engineer.assignSite(siteId);
            req.session().attribute("createdEngineer","Engineer was added successfully!");
            res.redirect("/engineers/"+engineerId+"/view");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/engineers/:id/delete", (req, res) -> {
            Map<String,Object> model = new HashMap<>();
            Engineer engineer = Engineer.find(Integer.parseInt(req.params("id")));
            engineer.delete();
            req.session().attribute("deletedEngineer","Engineer was deleted successfully!");
            res.redirect("/engineers");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/sites",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            List<Site> sites = Site.all();
            model.put("sites",sites);
            model.put("createdSite",req.session().attribute("createdSite"));
            req.session().removeAttribute("createdSite");
            model.put("updatedSite",req.session().attribute("updatedSite"));
            req.session().removeAttribute("updatedSite");
            model.put("deletedSite",req.session().attribute("deletedSite"));
            req.session().removeAttribute("deletedSite");
            return new ModelAndView(model,"site.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sites",(req,res) -> {
            Map<String,Object> model  = new HashMap<>();
            String name = req.queryParams("name");
            String town = req.queryParams("town");
            Site site   = new Site(name,town);
            site.save();
            req.session().attribute("createdSite","Site was added successfully!");
            res.redirect("/sites");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
