package com.tdp2.group152;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SaltGenerator {

    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[64];
        random.nextBytes(salt);

        return salt;
    }
}
