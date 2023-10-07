package Reclamation.com.example.Reclamation.Controller;

import Reclamation.com.example.Reclamation.Entity.Claim;
import Reclamation.com.example.Reclamation.Service.ClaimServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RestController
@RequestMapping("/claims")
public class ClaimController {
    private final ClaimServ claimServ;
    private final SecretKey secretKey; // Générez et gérez votre clé secrète de manière sécurisée.

    @Autowired
    public ClaimController(ClaimServ claimServ) {
        this.claimServ = claimServ;
        this.secretKey = generateSecretKey(); // Générez votre clé secrète ici.
    }
    // Méthode pour générer une clé secrète (à améliorer pour une gestion sécurisée)
    private SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // taille du clé
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/add")
    @ResponseBody
    public Claim addClaim(@RequestBody Claim claim) throws Exception {
        return claimServ.addClaim(claim);
    }
        @GetMapping("/getAll")
        @ResponseBody
        public List<Claim> getAllClaims() throws Exception {
            List<Claim> claims = claimServ.getAllClaims();
            // Déchiffrez les claims pour le responsable
            for (Claim claim : claims) {
                String decryptedClaimMsg = claimServ.decrypt(claim.getClaimMsg());
                claim.setClaimMsg(decryptedClaimMsg);
            }

            return claims;
        }

}

