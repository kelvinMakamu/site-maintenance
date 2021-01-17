package components.sites;

import java.sql.Timestamp;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;
        Site site = (Site) o;
        return id == site.id && getName().equals(site.getName()) && getTown().equals(site.getTown()) && Objects.equals(createdAt, site.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getTown(), createdAt);
    }
}