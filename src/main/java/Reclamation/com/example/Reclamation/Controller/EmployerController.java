package Reclamation.com.example.Reclamation.Controller;

import Reclamation.com.example.Reclamation.Entity.Employer;
import Reclamation.com.example.Reclamation.Service.EmployerServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/employers")
public class EmployerController {
    private final EmployerServ employerServ;

    @Autowired
    public EmployerController(EmployerServ employerServ) {
        this.employerServ = employerServ;
    }


    @PostMapping("/signup")
    @ResponseBody
    public Employer addEmployer(@RequestBody Employer employer) {
        return employerServ.addEmployer(employer);
    }

    @ResponseBody
    @PostMapping("/signin")
    public Employer signIn(@RequestParam String name, @RequestParam String password) {
        return employerServ.signIn(name, password);
    }
}
