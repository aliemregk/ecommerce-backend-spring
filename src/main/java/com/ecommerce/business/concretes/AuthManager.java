package com.ecommerce.business.concretes;

import org.springframework.stereotype.Service;

import com.ecommerce.business.abstracts.AuthService;
import com.ecommerce.business.abstracts.UserService;
import com.ecommerce.business.constants.Messages;
import com.ecommerce.business.requests.auth.LoginRequest;
import com.ecommerce.business.requests.auth.RegisterRequest;
import com.ecommerce.business.responses.auth.AuthResponse;
import com.ecommerce.business.responses.user.GetAllUserResponse;
import com.ecommerce.core.entities.User;
import com.ecommerce.core.utilities.mapper.MapperUtil;
import com.ecommerce.core.utilities.results.dataresults.DataResult;
import com.ecommerce.core.utilities.results.dataresults.ErrorDataResult;
import com.ecommerce.core.utilities.results.dataresults.SuccessDataResult;
import com.ecommerce.core.utilities.security.hashing.HashingUtil;
import com.ecommerce.core.utilities.security.jwt.JwtTokenUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public DataResult<User> register(RegisterRequest registerRequest) {
        DataResult<User> result = userService.getByEmail(registerRequest.getEmail());
        if (result.isSuccess()) {
            return new ErrorDataResult<>(Messages.EMAIL_ERR_MSG, null);
        }
        registerRequest.setPassword(HashingUtil.createPassword(registerRequest.getPassword()));
        return userService.add(registerRequest);
    }

    @Override
    public DataResult<User> login(LoginRequest loginRequest) {
        DataResult<User> result = userService.getByEmail(loginRequest.getEmail());
        if (!result.isSuccess()) {
            return new ErrorDataResult<>(result.getMessage(), null);
        }
        if (!HashingUtil.verifyPassword(loginRequest.getPassword(), result.getData().getPassword())) {
            return new ErrorDataResult<>(Messages.WRONG_PW_MSG, null);
        }

        return new SuccessDataResult<>(result.getData());
    }

    @Override
    public DataResult<AuthResponse> createToken(User user) {

        final String accessToken = jwtTokenUtil.createToken(user);
        if (accessToken.isEmpty() || accessToken.isBlank()) {
            return new ErrorDataResult<>(Messages.TOKEN_ERR_MSG, null);
        }
        return new SuccessDataResult<>(Messages.TOKEN_MSG,
                new AuthResponse(accessToken, MapperUtil.map(user, GetAllUserResponse.class)));
    }

}
