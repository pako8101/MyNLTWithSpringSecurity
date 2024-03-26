package bg.softuni.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{

    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @ManyToOne(optional = false)
    private Project project;
    public Employee(){

    }



    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
}
