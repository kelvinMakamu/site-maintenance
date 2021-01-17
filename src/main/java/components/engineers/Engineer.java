package components.engineers;

import components.data.Database;
import org.sql2o.Connection;

import java.sql.Timestamp;
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

    public int update(int engineerId, String updatedFirstName, String updatedLastName){
        Engineer foundEngineer = Engineer.find(engineerId);
        String foundFirstName  = foundEngineer.getFirstName();
        String foundLastName   = foundEngineer.getLastName();
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