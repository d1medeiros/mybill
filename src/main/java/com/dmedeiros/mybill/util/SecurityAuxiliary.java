package com.dmedeiros.mybill.util;

import com.dmedeiros.mybill.user.model.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityAuxiliary {

    protected static SecureRandom random = new SecureRandom();
    private final static int RADIX = 16;
    private final static String DIGEST_ALGORITHM = "SHA-512";
    private final static String CHARSET = "UTF-8";

    public static String generateToken(String word) {
        long longToken = Math.abs(random.nextLong()) + word.length();
        String random = Long.toString(longToken, RADIX);
        return random;
    }

    public static String digester(String original) {

        StringBuilder hexString = null;
        try {
            MessageDigest algorithm = MessageDigest.getInstance(DIGEST_ALGORITHM);
            byte messageDigest[] = algorithm.digest(original.getBytes(CHARSET));

            hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    public static void passwordDigester(User user) {
        String password = user.getPassword();
        password = digester(password);
        user.setPassword(password);
    }
}
