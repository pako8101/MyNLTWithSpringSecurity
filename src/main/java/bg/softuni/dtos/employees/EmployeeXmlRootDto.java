package bg.softuni.dtos.employees;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeXmlRootDto {
    @XmlElement(name = "employee")
private List<EmployeeImportDto> employee;

    public EmployeeXmlRootDto() {
    }

    public EmployeeXmlRootDto(List<EmployeeImportDto> employee) {
        this.employee = employee;
    }

    public List<EmployeeImportDto> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeImportDto> employee) {
        this.employee = employee;
    }
}
