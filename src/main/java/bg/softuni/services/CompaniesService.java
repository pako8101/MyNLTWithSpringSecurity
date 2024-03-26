package bg.softuni.services;

import jakarta.xml.bind.JAXBException;
import org.springframework.stereotype.Service;


public interface CompaniesService {
     void importCompanies () throws JAXBException;

    boolean areImported();
}
