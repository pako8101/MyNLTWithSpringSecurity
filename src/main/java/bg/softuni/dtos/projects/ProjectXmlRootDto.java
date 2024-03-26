package bg.softuni.dtos.projects;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "projects")
public class ProjectXmlRootDto {
    @XmlElement(name = "project")
    List<ProjectImportDto>projects;

    public ProjectXmlRootDto() {
    }

    public List<ProjectImportDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectImportDto> projects) {
        this.projects = projects;
    }
}
