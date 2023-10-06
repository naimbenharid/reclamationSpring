package Reclamation.com.example.Reclamation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate claimDate;
    private String claimMsg ;
    private String employerFullName;



    @JsonIgnore
  @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "responsible_id")
    private Responsible responsible;
}
