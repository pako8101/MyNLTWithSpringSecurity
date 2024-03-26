package bg.softuni.services;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface EmployeeService {
    boolean areImported();

    void importEntities() throws JAXBException, IOException;
}
