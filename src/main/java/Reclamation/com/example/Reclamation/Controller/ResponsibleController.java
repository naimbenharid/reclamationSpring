package Reclamation.com.example.Reclamation.Controller;

import Reclamation.com.example.Reclamation.Entity.Responsible;
import Reclamation.com.example.Reclamation.Service.ResponsibleServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RestController
@RequestMapping("/responsible") // L'URL de base pour toutes les requêtes liées aux responsables
public class ResponsibleController {

    private final ResponsibleServ responsibleServ;

    @Autowired
    public ResponsibleController(ResponsibleServ responsibleServ) {
        this.responsibleServ = responsibleServ;
    }

    @PostMapping("/signin")
    public Responsible signIn(@RequestParam String name, @RequestParam String password) {
        return responsibleServ.signIn(name, password);
    }
}
