package org.evoting.de.Response;

public class SignupResponse {
    private Long id; // Eindeutige ID des Benutzers
    private String message;

    public SignupResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
