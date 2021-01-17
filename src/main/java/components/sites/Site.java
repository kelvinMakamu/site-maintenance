package components.sites;

import components.data.Database;
import components.engineers.Engineer;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;
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

    public static List<Site> all(){
        String query = "SELECT * FROM sites";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query).executeAndFetch(Site.class);
        }
    }

    public static Site find(int siteId){
        String query = "SELECT * FROM sites WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",siteId)
                    .executeAndFetchFirst(Site.class);
        }
    }

    public int update(String updatedName, String updatedTown){
        int siteId         = this.id;
        String foundName   = this.name;
        String foundTown   = this.town;
        if(!foundName.equals(updatedName) && !foundTown.equals(updatedTown)){
            String query = "UPDATE sites SET name=:name, town=:town WHERE id=:id";
            try(Connection connection = Database.sql2o.open()){
                connection.createQuery(query)
                        .addParameter("name",updatedName)
                        .addParameter("town",updatedTown)
                        .addParameter("id",siteId)
                        .executeUpdate();
            }
            return 1000;
        }else if(!foundName.equals(updatedName) && foundTown.equals(updatedTown)){
            String query = "UPDATE sites SET name=:name WHERE id=:id";
            try(Connection connection = Database.sql2o.open()){
                connection.createQuery(query)
                        .addParameter("name",updatedName)
                        .addParameter("id",siteId)
                        .executeUpdate();
            }
            return 1002;
        }else if(foundName.equals(updatedName) && !foundTown.equals(updatedTown)){
            String query = "UPDATE sites SET town=:town WHERE id=:id";
            try(Connection connection = Database.sql2o.open()){
                connection.createQuery(query)
                        .addParameter("town",updatedTown)
                        .addParameter("id",siteId)
                        .executeUpdate();
            }
            return 1004;
        }else{
            return 1001;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Site)) return false;
        Site site = (Site) o;
        return getId() == site.getId() && getName().equals(site.getName()) && getTown().equals(site.getTown());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTown());
    }
}