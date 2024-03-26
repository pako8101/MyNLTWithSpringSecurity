package bg.softuni.services.impl;

import bg.softuni.dtos.companies.CompanyXmlRootDto;
import bg.softuni.entities.Company;
import bg.softuni.repositories.CompaniesRepository;
import bg.softuni.services.CompaniesService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CompaniesServiceImpl implements CompaniesService {
    private final CompaniesRepository companiesRepository;

    public CompaniesServiceImpl(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public void importCompanies() throws JAXBException {

        JAXBContext context =
                JAXBContext.newInstance(CompanyXmlRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        CompanyXmlRootDto rootDto = (CompanyXmlRootDto)
                unmarshaller.unmarshal(new File("C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\WorkShop062023Bankin\\ProjectMyTechnologies-main\\MVCProjectNextLEvelTechnologies\\src\\main\\resources\\files\\xmls\\companies.xml"));

        List<Company> companies =
                rootDto.getCompanies()
                        .stream()
                        .map(dto -> new Company(dto.getName()))
                        .toList();

        companiesRepository.saveAll(companies);


    }

    @Override
    public boolean areImported() {
        return this.companiesRepository.count()>0;
    }
}
