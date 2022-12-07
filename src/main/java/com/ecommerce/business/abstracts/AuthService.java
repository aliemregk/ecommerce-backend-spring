package com.ecommerce.business.abstracts;

import com.ecommerce.business.requests.auth.LoginRequest;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface AuthService {

    DataResult<User> register(RegisterRequest registerRequest);

    DataResult<User> login(LoginRequest loginRequest);

    DataResult<String> createToken(User user);

}
