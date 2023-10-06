package Reclamation.com.example.Reclamation.Service;

import Reclamation.com.example.Reclamation.Entity.Employer;
import Reclamation.com.example.Reclamation.Repository.EmployerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployerServ {
    private  final EmployerRepo employerRepo ;
    @Autowired
    public EmployerServ(EmployerRepo employerRepo){
        this.employerRepo=employerRepo ;

    }

    public Employer addEmployer(Employer employer) {
        return employerRepo.save(employer);
    }

    public Employer signIn(String name, String password) {
        Optional<Employer> employerOptional = employerRepo.findByName(name);

        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            if (employer.getPassword().equals(password)) {
                return employer;
            }
        }
        return null;
    }
}
