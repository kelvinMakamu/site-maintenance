package components.engineers;

import java.util.Objects;

public class Engineer {
    private int id;
    private String firstName;
    private String lastName;

    public Engineer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
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