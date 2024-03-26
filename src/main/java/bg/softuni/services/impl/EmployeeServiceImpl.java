package bg.softuni.services.impl;

import bg.softuni.dtos.companies.CompanyXmlRootDto;
import bg.softuni.dtos.employees.EmployeeImportDto;
import bg.softuni.dtos.employees.EmployeeXmlRootDto;
import bg.softuni.entities.Employee;
import bg.softuni.entities.Project;
import bg.softuni.repositories.EmployeesRepository;
import bg.softuni.repositories.ProjectsRepository;
import bg.softuni.services.EmployeeService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeesRepository employeesRepository;
    private final ProjectsRepository projectsRepository;
    private final ModelMapper modelMapper;
    private final static String PATH = "C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\WorkShop062023Bankin\\ProjectMyTechnologies-main\\MVCProjectNextLEvelTechnologies\\src\\main\\resources\\files\\xmls\\employees.xml";

    public EmployeeServiceImpl(EmployeesRepository employeesRepository, ProjectsRepository projectsRepository, ModelMapper modelMapper) {
        this.employeesRepository = employeesRepository;
        this.projectsRepository = projectsRepository;
        this.modelMapper = modelMapper;
    }
    public String getXmlInfo() throws IOException {

        return String.join("\n", Files.readString(Path.of("C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\WorkShop062023Bankin\\ProjectMyTechnologies-main\\MVCProjectNextLEvelTechnologies\\src\\main\\resources\\files\\xmls\\employees.xml")));

    }

    @Override
    public boolean areImported() {
        return employeesRepository.count()>0;
    }

    @Override
    public void importEntities() throws JAXBException, IOException {
        JAXBContext context =
                JAXBContext.newInstance(EmployeeXmlRootDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        EmployeeXmlRootDto rootDto = (EmployeeXmlRootDto)
                unmarshaller.unmarshal(new File(PATH));

        for (EmployeeImportDto employeeImportDto: rootDto.getEmployee()){
          Employee employee=  this.modelMapper.map(employeeImportDto, Employee.class);

            employee.setProject(this.projectsRepository.findByName(employee.getProject().getName()).get());

            this.employeesRepository.saveAndFlush(employee);
        }


//        List<Employee> employeeToSave=
//                rootDto.getEmployee()
//                        .stream()
//                        .map(employeeImportDto -> modelMapper.map(
//                                employeeImportDto, Employee.class
//                        )).map(this::addProject)
//                        .toList();
//        employeesRepository.saveAll(employeeToSave);

    }

    private Employee addProject(Employee employee) {

        Project project =
                this.projectsRepository.findFirstByName(employee
                        .getProject().getName()).get();
        employee.setProject(project);
        return employee;
    }
}
