package components.sites;

import java.sql.Timestamp;

public class Site {
    private int id;
    private String name;
    private String town;
    private Timestamp createdAt;

    public Site(String name, String town){
        this.name = name.toLowerCase();
        this.town = town.toLowerCase();;
    }

    public String getName(){
        return this.name;
    }
    
    public String getTown(){
        return this.town;
    }
}