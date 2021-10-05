package tutorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Tutorial {
    private String titel;
    private String name = "Darius Adrian";
    private String email = "test@gmail.com";
    private String dateiName;
    private String inhalt;
    private Boolean verschluesselt = true;
    private SecretKeySpec secret;

    public Tutorial(String titel, String name, String email, String inhalt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.titel = titel;
        this.name = name;
        this.email = email;
        this.inhalt = inhalt;
    }
    
    public Tutorial() {}

    // LeseDa
    public void leseAusDatei(File datei) {
        this.dateiName = datei.getName();
        System.out.println(this.dateiName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(datei));
            String text = "";
            String zeile = "";
            int index = 0;

            //String inhalt = entschluesseln();
            
            while ((zeile = reader.readLine()) != null) {
                if (index == 0) {
                    this.titel = zeile;
                } else if (index == 1) {

                } else {                
                	System.out.println(zeile);
                    text += zeile;
                }
                index++;
            }
            this.inhalt = text;
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void speichereAlsDatei() {
        if (this.getDateiName() == null) {
            String dateiname = titel + "-";

            for (int i = 0; i < 5; i++) {

                int zahl = (int) ((Math.random() * 25) + 97 );

                dateiname += ((char) zahl);
            }

            this.setDateiName(dateiname + ".tutorial");
        }

        

        try {
			inhalt = verschluesseln(inhalt);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        String datei = titel + "\n|*****|\n" + inhalt;

        
        Path path = Paths.get("./Tutorials");

        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("New Directory created !   " + path);
        } else {
            System.out.println("Done");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Tutorials/" + this.getDateiName()));
            writer.write(datei);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String verschluesseln(String unentschluesseltes) throws Exception{        
        // Verschluesseln
        Cipher cipher = Cipher.getInstance("AES");

        // Cipher auf Verschlüsselungsmodus schalten
        cipher.init(Cipher.ENCRYPT_MODE, this.secret);

        // Verschlüsseln
        byte[] encrypted = cipher.doFinal(unentschluesseltes.getBytes());
        
        // bytes zu Base64-String konvertieren (dient der Lesbarkeit)
        Base64.Encoder encoder= Base64.getEncoder();

        // Verschlüsseltes als String darstellen
        String verschluesseltes = encoder.encodeToString(encrypted);
        
        // Ergebnis
        return verschluesseltes;     
    }

    
    public void entschluesseln() throws Exception {
        if (this.verschluesselt == true) {
        	Base64.Decoder decoder = Base64.getDecoder();

            //Der Inhalt wird in bytes umgewandelt
            byte[] verschluesseltesBytes = (this.inhalt).getBytes("UTF-8");

            //Die einzelnen Bytes werden decodet
            byte[] crypted2 = decoder.decode(verschluesseltesBytes);
             
            // Entschluesseln
            Cipher cipher2 = Cipher.getInstance("AES");
            cipher2.init(Cipher.DECRYPT_MODE, this.secret);
            byte[] cipherData2 = cipher2.doFinal(crypted2);
            
            String entschluesseltes = new String(cipherData2, "UTF-8");
             
            // Klartext
            this.verschluesselt = false;
            this.inhalt = entschluesseltes;
        }
    }

    public void setzeSecret(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        // byte-Array erzeugen
        byte[] key = (password).getBytes("UTF-8");
        
        // aus dem Array einen Hash erzeugen mit SHA
        MessageDigest sha = MessageDigest.getInstance("SHA-256");

        key = sha.digest(key);

        // nur die ersten 128 bit nutzen
        key = Arrays.copyOf(key, 16); 
        

        this.secret = new SecretKeySpec(key, "AES");
    }
    
    
    public String[] listeSeiten() {
        String[] seiten = this.inhalt.split("#####");
        return seiten;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateiName() {
        return dateiName;
    }

    public void setDateiName(String dateiName) {
        this.dateiName = dateiName;
    }

	public Boolean getVerschluesselt() {
		return verschluesselt;
	}

	public void setVerschluesselt(Boolean verschluesselt) {
		this.verschluesselt = verschluesselt;
	}
}
