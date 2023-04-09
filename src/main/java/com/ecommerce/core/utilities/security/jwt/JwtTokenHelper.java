package com.ecommerce.core.utilities.security.jwt;

import com.ecommerce.core.entities.concretes.User;

public interface JwtTokenHelper {

    String createToken(User user);
}
