package com.ecommerce.core.utilities.security.hashing;

public interface HashingService {
    
    String createPassword(String password);

    boolean verifyPassword(String password, String passwordHash) ;
}
