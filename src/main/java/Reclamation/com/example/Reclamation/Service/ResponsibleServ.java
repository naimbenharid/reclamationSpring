package Reclamation.com.example.Reclamation.Service;

import Reclamation.com.example.Reclamation.Entity.Responsible;
import Reclamation.com.example.Reclamation.Repository.ResponsibleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponsibleServ {
    private final ResponsibleRepo responsibleRepo;
    @Autowired

    public ResponsibleServ(ResponsibleRepo responsibleRepo) {
        this.responsibleRepo = responsibleRepo;
    }
    public Responsible signIn(String name, String password) {
        Optional<Responsible> responsibleOptional = responsibleRepo.findByName(name);

        if (responsibleOptional.isPresent()) {
            Responsible responsible = responsibleOptional.get();
            if (responsible.getPassword().equals(password)) {
                return responsible;
            }
        }
        return null;
    }
}
