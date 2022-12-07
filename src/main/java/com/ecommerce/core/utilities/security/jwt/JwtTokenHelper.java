package com.ecommerce.core.utilities.security.jwt;

import com.ecommerce.core.entities.User;

public interface JwtTokenHelper {

    String createToken(User user);
}
