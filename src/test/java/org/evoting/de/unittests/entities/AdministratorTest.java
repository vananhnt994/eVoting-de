package org.evoting.de.unittests.entities;

import org.evoting.de.user.domain.model.admin.Administrator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {

    @Test
    public void testConstructorAndGetters() {
        String id = "admin123";
        String email = "admin@example.com";
        String password = "securePassword";

        Administrator admin = new Administrator(id, email, password);

        assertEquals(id, admin.getId());
        assertEquals(email, admin.getEmail());
        assertEquals(password, admin.getPassword());
    }

    @Test
    public void testSetters() {
        Administrator admin = new Administrator("admin123", "admin@example.com", "securePassword");

        // Teste Setzen neuer Werte
        String newId = "admin456";
        String newEmail = "newAdmin@example.com";
        String newPassword = "newSecurePassword";

        admin.setId(newId);
        admin.setEmail(newEmail);
        admin.setPassword(newPassword);

        assertEquals(newId, admin.getId());
        assertEquals(newEmail, admin.getEmail());
        assertEquals(newPassword, admin.getPassword());
    }
}