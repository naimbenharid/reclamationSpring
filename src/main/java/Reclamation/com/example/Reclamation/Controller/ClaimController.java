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
            keyGenerator.init(128); // Utilisez la taille de clé souhaitée (128, 256, etc.).
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("/add")
    @ResponseBody
    public Claim addClaim(@RequestBody Claim claim) throws Exception {
        // Chiffrement du champ claimMsj en utilisant la clé secrète
        String encryptedClaimMsj = claimServ.Crypte(claim.getClaimMsg(), secretKey);
        claim.setClaimMsg(encryptedClaimMsj);

        // Enregistrement du claim chiffré dans la base de données
        return claimServ.addClaim(claim, secretKey);
    }
        @GetMapping("/getAll")
    @ResponseBody
    public List<Claim> getAllClaims() {
            return claimServ.getAllClaims();
        }
    }

