package Reclamation.com.example.Reclamation.Repository;

import Reclamation.com.example.Reclamation.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepo  extends JpaRepository<Claim,Long> {
}
