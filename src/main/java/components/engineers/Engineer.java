package components.engineers;

import components.data.Database;
import org.sql2o.Connection;

import java.util.Objects;

public class Engineer {
    private int id;
    private String firstName;
    private String lastName;

    public Engineer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName  = lastName;
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