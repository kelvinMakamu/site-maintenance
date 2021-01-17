package components.engineers;

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

}