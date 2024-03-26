package bg.softuni.dtos.companies;

import jakarta.xml.bind.annotation.*;

//@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyImportDto {
    @XmlAttribute
    private String name;

    public CompanyImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
