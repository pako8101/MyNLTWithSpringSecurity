package bg.softuni.controllers;

import bg.softuni.services.CompaniesService;
import bg.softuni.services.EmployeeService;
import bg.softuni.services.ProjectsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CompaniesService companiesService;
    private final ProjectsService projectsService;
    private final EmployeeService employeeService;

    public HomeController(CompaniesService companiesService, ProjectsService projectsService, EmployeeService employeeService) {
        this.companiesService = companiesService;
        this.projectsService = projectsService;
        this.employeeService = employeeService;
    }

//    @GetMapping("/")
//    public ModelAndView index(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        modelAndView.addObject("firstName","Simona");
//        return modelAndView;
//    }

    @GetMapping("/home")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String home(Model model){

        boolean areImported = this.companiesService.areImported() &&
                this.projectsService.areImported() &&
                this.employeeService.areImported();

model.addAttribute("areImported",areImported);
model.addAttribute("title","home");
        return "home";
    }

}
