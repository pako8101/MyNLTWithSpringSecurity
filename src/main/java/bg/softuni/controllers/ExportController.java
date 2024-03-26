package bg.softuni.controllers;

import bg.softuni.services.EmployeeService;
import bg.softuni.services.ProjectsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("export")
public class ExportController {
private final ProjectsService projectsService;

private final EmployeeService employeeService;


    public ExportController(ProjectsService projectsService, EmployeeService employeeService) {
        this.projectsService = projectsService;
        this.employeeService = employeeService;
    }
@GetMapping("/project-if-finished")
    public String getAllFinishedProjects(Model model){
        final String allFinishedProjects = this.projectsService
                .getAllFinishedProjects();
        model.addAttribute("projectsIfFinished",allFinishedProjects);

        return "export/export-project-if-finished";
}
}
