package com.ecommerce.core.utilities.security.hashing;

import org.springframework.security.crypto.bcrypt.BCrypt;

public final class HashingUtil {

    private static final String SALT = BCrypt.gensalt();

    private HashingUtil() {
    }

    public static String createPassword(String password) {
        return BCrypt.hashpw(password, SALT);
    }

    public static boolean verifyPassword(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
