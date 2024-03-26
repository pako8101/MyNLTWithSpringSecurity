package bg.softuni.controllers;

import bg.softuni.services.CompaniesService;
import bg.softuni.services.EmployeeService;
import bg.softuni.services.ProjectsService;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class ImportController {

    private final ProjectsService projectsService;
    private final EmployeeService employeeService;

    private final CompaniesService companiesService;
@Autowired
    public ImportController(ProjectsService projectsService, EmployeeService employeeService, CompaniesService companiesService) {
    this.projectsService = projectsService;
    this.employeeService = employeeService;
    this.companiesService = companiesService;
    }

    @GetMapping("/import/xml")
    public ModelAndView index (){
    ModelAndView modelAndView = new ModelAndView("xml/import-xml");
        boolean[]importedStatuses = {this.companiesService.areImported(),
        this.projectsService.areImported(),
        this.employeeService.areImported()};
    modelAndView.addObject("areImported",importedStatuses);
        return modelAndView;

//        model.addAttribute("areImported", importedStatuses);
//
//        return "xml/import-xml";
    }
    @GetMapping("/import/companies")
    public String importCompanies(Model model) throws IOException {
        String fileContent = getFileContent("companies.xml");

        model.addAttribute("companies",fileContent);

        return "xml/import-companies";


    }
    @PostMapping("/import/companies")
    public String doImportCompanies() throws JAXBException {
companiesService.importCompanies();
       // model.addAttribute("areImported", new boolean[] {false,false,false});
return "redirect:/import/xml";

    }
@GetMapping("/import/projects")
    public String importProjects(Model model) throws IOException {
    String fileContent = getFileContent("projects.xml");

    model.addAttribute("projects",fileContent);
    return "xml/import-projects";
}
@PostMapping("import/projects")
public String doImportProjects() throws JAXBException {
    projectsService.importProjects();

    return "redirect:/import/xml";
}

@GetMapping("import/employees")
public String getImportEmployees(Model model) throws IOException {

    String fileContent = getFileContent("employees.xml");
    model.addAttribute("employees",fileContent);
return "xml/import-employees";

}
@PostMapping("import/employees")
public ModelAndView postEmployees() throws JAXBException, IOException {
    ModelAndView modelAndView = new ModelAndView("redirect:/import/xml");
   // modelAndView.addObject("employees", getFileContent("employees.xml"));
    this.employeeService.importEntities();
    return modelAndView;
//    this.employeeService.importEntities();
//    return "redirect:/import/xml";
}



    private String getFileContent(String fileName) throws IOException {
        String xmlContent = Files.readString(Path.
                of("C:\\Users\\Plamen Kamenov\\Desktop\\MyPrograming\\exercises\\SpringData\\WorkShop062023Bankin\\ProjectMyTechnologies-main\\MVCProjectNextLEvelTechnologies\\src\\main\\resources\\files\\xmls\\",fileName));

return String.join("\n",xmlContent);

    }
}
