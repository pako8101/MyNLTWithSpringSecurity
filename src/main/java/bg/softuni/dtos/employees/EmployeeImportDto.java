package bg.softuni.dtos.employees;

import bg.softuni.dtos.projects.ProjectImportDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employee")
public class EmployeeImportDto implements Serializable {
    @XmlElement(name = "first_name")
    private String firstName;
    @XmlElement(name = "last_name")
    private String lastName;
    @XmlElement
    private int age;
    @XmlElement(name = "project")
    private ProjectImportDto project;

    public EmployeeImportDto() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProjectImportDto getProject() {
        return project;
    }

    public void setProject(ProjectImportDto project) {
        this.project = project;
    }
}
