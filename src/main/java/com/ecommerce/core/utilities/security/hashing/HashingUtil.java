package com.ecommerce.core.utilities.security.hashing;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashingUtil implements HashingService {

    private static final String SALT = BCrypt.gensalt();

    @Override
    public String createPassword(String password) {
        return BCrypt.hashpw(password, SALT);
    }

    @Override
    public boolean verifyPassword(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }
}
