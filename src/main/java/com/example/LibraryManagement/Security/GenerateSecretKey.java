package com.example.LibraryManagement.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;


public class GenerateSecretKey {
    public static void main(String[] args) throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
        generator.init(256);

        SecretKey key = generator.generateKey();

        System.out.println(
                Base64.getEncoder().encodeToString(key.getEncoded())
        );
    }
}