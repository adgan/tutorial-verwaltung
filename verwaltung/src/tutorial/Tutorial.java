package tutorial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Tutorial {
    private String titel;
    private String name ="Darius Adrian";
    private String email ="test@gmail.com";
    private String inhalt;

    public Tutorial(String titel, String name, String email, String inhalt) {
        this.titel = titel;
        this.name = name;
        this.email = email;
        this.inhalt = inhalt;
    }

    public void speichereAlsDatei () {
        String dateiname = titel + "-";
        
        String datei =  titel + "\n|*****|\n" + inhalt;

        String alphabet = "abcdefghijklmnopqrstuvxyz";

        for (int i = 0; i < 5; i++) {
            int zahl = (int) (Math.random() * 25);
            dateiname += alphabet.charAt(zahl);
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dateiname + ".tutorial"));
            writer.write(datei);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
