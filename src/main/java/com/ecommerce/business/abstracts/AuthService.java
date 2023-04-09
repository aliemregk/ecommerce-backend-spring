package com.ecommerce.business.abstracts;

import com.ecommerce.business.requests.auth.LoginRequest;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.business.responses.auth.AuthResponse;
import com.ecommerce.core.entities.concretes.User;
import com.ecommerce.core.utilities.results.dataresults.DataResult;

public interface AuthService {

    User register(RegisterRequest registerRequest);

    User login(LoginRequest loginRequest);

    DataResult<AuthResponse> createAuthResponse(User user);

}
