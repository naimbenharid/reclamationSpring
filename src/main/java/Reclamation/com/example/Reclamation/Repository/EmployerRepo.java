package Reclamation.com.example.Reclamation.Repository;

import Reclamation.com.example.Reclamation.Entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployerRepo extends JpaRepository<Employer,Long> {
    Optional<Employer> findByName(String name);
}
