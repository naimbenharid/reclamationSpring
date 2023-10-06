package Reclamation.com.example.Reclamation.Service;

import Reclamation.com.example.Reclamation.Entity.Claim;
import Reclamation.com.example.Reclamation.Repository.ClaimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;


@Service
public class ClaimServ {
    private final ClaimRepo claimRepo ;
    @Autowired
     public  ClaimServ (ClaimRepo claimRepo){
        this.claimRepo = claimRepo ;
    }



    public List<Claim> getAllClaims(){
        return claimRepo.findAll();
    }

    // Méthode pour chiffrer un texte
    public String Crypte (String message, SecretKey secretKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }


    public Claim addClaim(Claim claim, SecretKey secretKey) throws Exception {
        String encryptedClaimMsg = Crypte(claim.getClaimMsg(), secretKey);
        claim.setClaimMsg(encryptedClaimMsg);
        return claimRepo.save(claim);
    }

    // Méthode pour déchiffrer un texte
    public String decrypt(String encryptedMessage, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
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