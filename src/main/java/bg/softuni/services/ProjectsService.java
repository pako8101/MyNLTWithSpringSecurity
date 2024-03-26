package bg.softuni.services;

import jakarta.xml.bind.JAXBException;

public interface ProjectsService {

    void importProjects() throws JAXBException;

    String getAllFinishedProjects();

    boolean areImported();
}
