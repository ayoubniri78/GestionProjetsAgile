package com.ensa_agadir.agile_app.user.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilitaire pour le hashage et la vérification des mots de passe
 * Utilise SHA-256 pour simplifier (en production, utiliser BCrypt)
 */
public class PasswordUtil {
    
    private PasswordUtil() {
        // Utility class
    }
    
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hashage du mot de passe", e);
        }
    }
    
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = hashPassword(password);
        return hashedInput.equals(hashedPassword);
    }
    
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
