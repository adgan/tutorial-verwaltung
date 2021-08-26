public class Tutorial {
    private String titel;
    private String name;
    private String email;
    private String inhalt;

    public Tutorial(String titel, String name, String email, String inhalt) {
        this.titel = titel;
        this.name = name;
        this.email = email;
        this.inhalt = inhalt;
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
