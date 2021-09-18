package tutorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tutorial {
    private String titel;
    private String name ="Darius Adrian";
    private String email ="test@gmail.com";
    private String dateiName;
    private String inhalt;

    public Tutorial(String titel, String name, String email, String inhalt) {
        this.titel = titel;
        this.name = name;
        this.email = email;
        this.inhalt = inhalt;
    }
    public Tutorial() {

    }

    public void leseAusDatei (File datei) {
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(datei));
        	String text = "";
            String zeile = "";
            int index = 0;
        	while((zeile = reader.readLine()) != null) {
                if (index == 0) {
                    this.titel = zeile;
                } else if (index == 1) {

                }
                else {
                    text += zeile + "\n";
                }
                index++;
            }
            this.inhalt = text;
            reader.close();

            //Titel einfügen
            //Prüfen, ob neue Seite --> Neues Textfield (Variablen für Abstand erstellen)
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public void speichereAlsDatei () {
        if (this.dateiName == null) {

            String alphabet = "abcdefghijklmnopqrstuvxyz";

            String dateiname = titel + "-";

            for (int i = 0; i < 5; i++) {

                int zahl = (int) (Math.random() * 25);

                dateiname += alphabet.charAt(zahl);
            }

            this.dateiName = dateiname;
        }

        String datei =  titel + "\n|*****|\n" + inhalt;
        
        
        Path path = Paths.get("./Tutorials");
        
        if (!Files.exists(path)) {
        	try {
				Files.createDirectory(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	System.out.println("New Directory created !   "+ path);
        } else {
        	System.out.println("Directory already exists");
        }
    

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./Tutorials/" + this.dateiName + ".tutorial"));
            writer.write(datei);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String[] listeSeiten() {
        String[] seiten = this.inhalt.split("|#####|");
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
}
