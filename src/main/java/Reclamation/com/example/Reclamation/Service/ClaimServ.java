package Reclamation.com.example.Reclamation.Service;

import Reclamation.com.example.Reclamation.Entity.Claim;
import Reclamation.com.example.Reclamation.Repository.ClaimRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ClaimServ {
    private final ClaimRepo claimRepo ;

    private static final String PASSWORD = "password";
    private static final String CRYPTING_KEY = "12334567";

    @Autowired
     public  ClaimServ (ClaimRepo claimRepo){
        this.claimRepo = claimRepo ;
    }


    public List<Claim> getAllClaims(){
        return claimRepo.findAll();
    }

    // Méthode pour chiffrer un texte
    public String encodeMessage(String message) {
        TextEncryptor encryptor = Encryptors.text(PASSWORD,CRYPTING_KEY);
        log.info( "encryptor" + encryptor.encrypt(message));
        return  encryptor.encrypt(message);
    }


    public Claim addClaim(Claim claim)  {
        String encryptedClaimMsg = encodeMessage(claim.getClaimMsg());
        claim.setClaimMsg(encryptedClaimMsg);
        return claimRepo.save(claim);
    }

    // Méthode pour déchiffrer un texte
    public String decrypt(String encryptedMessage)  {
        TextEncryptor encryptor = Encryptors.text(PASSWORD,CRYPTING_KEY);
        return encryptor.decrypt(encryptedMessage);
    }




}





/*public Claim addClaim(Claim claim) {
        try {
            // Message à chiffrer
            String originalClaimMsg = claim.getClaimMsg();

            // Exécutez la commande OpenSSL pour chiffrer le message
            Process process = Runtime.getRuntime().exec("openssl enc -aes-256-cbc -base64 -out C:\\Users\\Naimb\\OneDrive\\Desktop\\claim.txt");

            // Écrivez le message original dans le flux d'entrée du processus
            process.getOutputStream().write(originalClaimMsg.getBytes(StandardCharsets.UTF_8));
            process.getOutputStream().close();

            // Attendre que le processus se termine
            process.waitFor();

            // Lire le résultat chiffré depuis le fichier de sortie
            String encryptedClaimMsg = Files.readString(Paths.get("claim.txt"));

            // Stockez le résultat chiffré dans l'objet Claim
            claim.setClaimMsg(encryptedClaimMsg);

            // Enregistrez la réclamation dans la base de données

            return claimRepo.save(claim);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Claim> getAllClaims() {
        return claimRepo.findAll();
    }*/