package com.dmedeiros.mybill.util;

import com.dmedeiros.mybill.bill.service.BillAndWalletService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityAuxiliary {

    protected static SecureRandom random = new SecureRandom();

    public static synchronized String generateToken(String word) {
        long longToken = Math.abs(random.nextLong()) + word.length();
        String random = Long.toString(longToken, 16);
        return random;
    }

    public static synchronized String digester(String original) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
        byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
