package bg.softuni.services.impl;

import bg.softuni.dtos.projects.ProjectImportDto;
import bg.softuni.dtos.projects.ProjectXmlRootDto;
import bg.softuni.entities.Company;
import bg.softuni.entities.Project;
import bg.softuni.repositories.CompaniesRepository;
import bg.softuni.repositories.ProjectsRepository;
import bg.softuni.services.ProjectsService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectsServiceImpl implements ProjectsService {
    private final ProjectsRepository projectsRepository;
    private final ModelMapper modelMapper;

    private final CompaniesRepository companiesRepository;
@Autowired
    public ProjectsServiceImpl(ProjectsRepository projectsRepository, ModelMapper modelMapper, CompaniesRepository companiesRepository) {
        this.projectsRepository = projectsRepository;
        this.modelMapper = modelMapper;
        this.companiesRepository = companiesRepository;
    }

    @Override
    public void importProjects() throws JAXBException {

        JAXBContext context =
                JAXBContext.newInstance(ProjectXmlRootDto.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        //unmarshaller.setAdapter(LocalDate.class,new LocalDateAdapter());

        ProjectXmlRootDto rootDto = (ProjectXmlRootDto)
                unmarshaller.unmarshal(
                new File("C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\WorkShop062023Bankin\\ProjectMyTechnologies-main\\MVCProjectNextLEvelTechnologies\\src\\main\\resources\\files\\xmls\\projects.xml")
        );

        List<Project> projects = rootDto.getProjects().stream()
                .map(this::toEntity)
                .filter(Objects::nonNull)
                .toList();
        projectsRepository.saveAll(projects);

    }

    @Override
    public String getAllFinishedProjects() {
    return  this.projectsRepository
            .findAllByisFinished(true)
            .stream()
            .map(p-> modelMapper.map(p, Project.class))
            .map(Project::toString)
            .collect(Collectors.joining(System.lineSeparator()));


    }

    @Override
    public boolean areImported() {
        return this.projectsRepository.count()>0;
    }

    private Project toEntity(ProjectImportDto projectImportDto) {

     Project project =
             modelMapper.map(projectImportDto, Project.class);

    Optional<Company>company = companiesRepository.findByName(
             projectImportDto.getCompany().getName());

    if (company.isEmpty()){
        return null;
    }
    project.setCompany(company.get());

    return project;
    }
}
