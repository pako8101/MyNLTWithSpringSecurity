package bg.softuni.controllers;

import bg.softuni.dtos.users.UserLoginDTO;
import bg.softuni.dtos.users.UserRegisterDTO;
import bg.softuni.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @PostMapping("/login")
    public  ModelAndView doLogin(@Valid UserLoginDTO loginDTO, BindingResult bindingResult){
       if (loginDTO.getUsername().equals("admin")&&
       loginDTO.getPassword().equals("secure")){
           ModelAndView result = new ModelAndView();
           result.setViewName("redirect:/home");
           return result;
       }



List<String> errors =  bindingResult
        .getAllErrors().stream()
        .map(e->e.getObjectName() + " " + e.getDefaultMessage())
        .toList();

        ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("user/login");
       modelAndView.addObject("errors",errors);

        return modelAndView;
    }

    @GetMapping("/register")
    public String register(){
        return "/user/register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO registerDTO){

boolean success = userService.register(registerDTO);

if (success){
    return "redirect:user/login";
}
        return "/user/register";
    }

@GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "home";
}
}
