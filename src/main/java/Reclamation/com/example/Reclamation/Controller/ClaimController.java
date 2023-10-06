package Reclamation.com.example.Reclamation.Controller;

import Reclamation.com.example.Reclamation.Entity.Claim;
import Reclamation.com.example.Reclamation.Service.ClaimServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/claims")
public class ClaimController {
    private final ClaimServ claimServ;

        @Autowired
        public ClaimController(ClaimServ claimServ) {
            this.claimServ = claimServ;
        }

        @PostMapping("/add")
        @ResponseBody
        public Claim addClaim(@RequestBody Claim claim) {
            return claimServ.addClaim(claim);
        }
        @GetMapping("/getAll")
    @ResponseBody
    public List<Claim> getAllClaims() {
            return claimServ.getAllClaims();
        }
    }

