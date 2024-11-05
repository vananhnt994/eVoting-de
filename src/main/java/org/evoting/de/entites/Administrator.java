package org.evoting.de.entites;

import jakarta.persistence.Entity;

public class Administrator {
    private String id;
    private String email;
    private String password;

    // Constructor
    public Administrator(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}