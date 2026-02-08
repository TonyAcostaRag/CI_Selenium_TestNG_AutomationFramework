package api.models;

public class Employee {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private int dependants;
    private float salary;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserName() {
        return this.username;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setDependants(int dependants) {
        this.dependants = dependants;
    }

    public int getDependants() {
        return this.dependants;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getSalary() {
        return this.salary;
    } 

}
