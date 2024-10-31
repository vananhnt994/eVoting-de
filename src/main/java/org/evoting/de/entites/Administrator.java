package org.evoting.de.entites;

public class Administrator {
    private String id;
    private String email;
    private String passwort;

    // Konstruktor
    public Administrator(String id, String email, String passwort) {
        this.id = id;
        this.email = email;
        this.passwort = passwort;
    }

    // Getter und Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}