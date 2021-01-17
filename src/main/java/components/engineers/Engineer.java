package components.engineers;

import components.data.Database;
import components.sites.Site;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Engineer {
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;

    public Engineer(String firstName, String lastName) {
        this.firstName = firstName.toLowerCase();
        this.lastName  = lastName.toLowerCase();
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public Timestamp getCreatedAt(){
        return this.createdAt;
    }

    public void save(){
        String query = "INSERT INTO engineers(firstName,lastName,createdAt)" +
                " VALUES(:firstName,:lastName,now())";
        try(Connection connection = Database.sql2o.open()){
            this.id = (int) connection.createQuery(query,true)
                    .addParameter("firstName",this.firstName)
                    .addParameter("lastName",this.lastName)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Engineer> all(){
        String query = "SELECT * FROM engineers";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query).executeAndFetch(Engineer.class);
        }
    }

    public static Engineer find(int engineerId){
        String query = "SELECT * FROM engineers WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",engineerId)
                    .executeAndFetchFirst(Engineer.class);
        }
    }

    public int update(String updatedFirstName, String updatedLastName){
        int engineerId         = this.id;
        String foundFirstName  = this.firstName;
        String foundLastName   = this.lastName;
        if(!foundFirstName.equals(updatedFirstName) && !foundLastName.equals(updatedLastName)){
            String query = "UPDATE engineers SET firstName=:firstName, lastName=:lastName WHERE id=:id";
            try(Connection connection = Database.sql2o.open()){
                connection.createQuery(query)
                    .addParameter("firstName",updatedFirstName)
                    .addParameter("lastName",updatedLastName)
                    .addParameter("id",engineerId)
                    .executeUpdate();
            }
            return 1000;
        }else if(!foundFirstName.equals(updatedFirstName) && foundLastName.equals(updatedLastName)){
            String query = "UPDATE engineers SET firstName=:firstName WHERE id=:id";
            try(Connection connection = Database.sql2o.open()){
                connection.createQuery(query)
                    .addParameter("firstName",updatedFirstName)
                    .addParameter("id",engineerId)
                    .executeUpdate();
            }
            return 1002;
        }else if(foundFirstName.equals(updatedFirstName) && !foundLastName.equals(updatedLastName)){
            String query = "UPDATE engineers SET lastName=:lastName WHERE id=:id";
            try(Connection connection = Database.sql2o.open()){
                connection.createQuery(query)
                    .addParameter("lastName",updatedLastName)
                    .addParameter("id",engineerId)
                    .executeUpdate();
            }
            return 1004;
        }else{
           return 1001;
        }
    }

    public void delete(){
        String query = "DELETE FROM engineers WHERE id=:id";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }

    public static void deleteAll(){
        String query = "DELETE FROM engineers";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(query).executeUpdate();
        }
    }

    public void assignSite(int siteId){
        String query = "INSERT INTO engineer_site(engineerId,siteId,createdAt) " +
                "VALUES(:engineerId,:siteId, now())";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(query)
                    .addParameter("engineerId",this.id)
                    .addParameter("siteId",siteId)
                    .executeUpdate();
        }
    }

    public List<Site> getAssignedSites(){
        String joinQuery = "SELECT siteId FROM engineer_site WHERE engineerId=:engineerId";
        try(Connection connection = Database.sql2o.open()){
            List<Integer> siteIds = connection.createQuery(joinQuery)
                    .addParameter("engineerId",this.id)
                    .executeAndFetch(Integer.class);
            List<Site> assignedSites = new ArrayList<>();
            for(Integer siteId: siteIds){
                String query = "SELECT * FROM sites WHERE id=:id";
                Site site    = connection.createQuery(query)
                        .addParameter("id",siteId)
                        .executeAndFetchFirst(Site.class);
                assignedSites.add(site);
            }
            return assignedSites;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;
        Engineer engineer = (Engineer) o;
        return id == engineer.id && getFirstName().equals(engineer.getFirstName()) && getLastName().equals(engineer.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstName(), getLastName());
    }
}