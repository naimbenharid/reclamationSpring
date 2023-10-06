package Reclamation.com.example.Reclamation.Repository;

import Reclamation.com.example.Reclamation.Entity.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsibleRepo extends JpaRepository<Responsible,Long> {
     Optional <Responsible> findByName (String name);
}

