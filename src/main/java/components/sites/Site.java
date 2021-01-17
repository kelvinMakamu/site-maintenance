package components.sites;

import components.data.Database;
import org.sql2o.Connection;

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

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
    public String getTown(){
        return this.town;
    }

    public Timestamp getCreatedAt(){
        return this.createdAt;
    }

    public void save(){
        String query = "INSERT INTO sites(name,town,createdAt)" +
                " VALUES(:name,:town,now())";
        try(Connection connection = Database.sql2o.open()){
            this.id = (int) connection.createQuery(query,true)
                    .addParameter("name",this.name)
                    .addParameter("town",this.town)
                    .executeUpdate()
                    .getKey();
        }
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